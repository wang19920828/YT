package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.MessageVO;
import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.order.OrderBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplySubmitInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryPagingVO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTrySumVO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectAddInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.redProject.RedProjectListOutPO;
import org.fh.general.ecom.common.vo.order.order.OrderBgDetailVO;
import org.fh.general.ecom.common.vo.order.redAudit.RedAuditBgDetailVO;
import org.fh.general.ecom.common.vo.order.redProject.RedProjectBgDetailVO;
import org.fh.general.ecom.common.vo.order.redProject.RedProjectListVO;
import org.fh.general.ecom.order.model.RedProject;
import org.fh.general.ecom.order.service.RedProjectService;
import org.fh.general.ecom.order.service.impl.TimedTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分红项目管理表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class RedProjectController {

    @Autowired
    private RedProjectService redProjectService;


    /**
     * 分页列表
     * */
    @RequestMapping("REP8005")
    public PagingVO findPage(RedProjectListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            RedProjectListOutputDTO dtoEntity= this.redProjectService.findPage(dto);
            List<RedProjectListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<RedProjectListVO> listvo=new ArrayList<RedProjectListVO>();
            list.forEach((RedProjectListOutPO temp) -> {
                RedProjectListVO voEn=new RedProjectListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }

    /**
     * 分红项目详情
     * id --分红项目主键
     * */
    @RequestMapping("REP8002")
    public BaseVO detailBgRedProject(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            RedProjectBgDetailOutputDTO response= this.redProjectService.detailBgRedProject(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            RedProjectBgDetailVO vo=new RedProjectBgDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            baseVO.exception();
            e.printStackTrace();
        }
        return  baseVO;
    }


    /**
     * 分红申请-试算
     * @param dto
     * @return
     */
    @RequestMapping("REP8003")
    public PagingExtensionVO redApplyTry(RedApplyTryInputDTO dto) {
        PagingExtensionVO pagingVO = new PagingExtensionVO();
        RedApplyTryOutputDTO outDto=this.redProjectService.redApplyTry(dto);
        if(!OutEnum.SUCCESS.getCode().equals(outDto.getMsg().getCode())){
            pagingVO.setMsg(outDto.getMsg());
            return pagingVO;
        }
        if(outDto.getFormulaList()==null || outDto.getFormulaList().size()==0){
            pagingVO.noData();
            return pagingVO;
        }

        RedApplyTrySumVO  sumVO=new RedApplyTrySumVO();
        sumVO.setPreShareRedTotal(outDto.getRealShareRedTotal());
        sumVO.setRealShareRedTotal(outDto.getRealShareRedTotal());
        if(outDto!=null){
            pagingVO.success(outDto.getFormulaList(),outDto.getPageInfo(),sumVO);
            return pagingVO;
        }else{
            pagingVO.noData();
            return pagingVO;
        }
    }

    /**
     * 分红申请-提交审核
     * @param dto
     * @return
     */
    @RequestMapping("REP8004")
    public BaseVO redApplySubmit(RedApplySubmitInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        String code=this.redProjectService.redApplySubmit(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    @Autowired
    private TimedTask timedTask;


    /**
     * 认购过了冷静期是否仍募资满额 任务
     * @return
     */
    @RequestMapping("REP8006")
    public BaseVO  executeAfterCool(){
        BaseVO baseVO=new BaseVO();
        baseVO= this.timedTask.executeAfterCool();
        return baseVO;
    }


}
