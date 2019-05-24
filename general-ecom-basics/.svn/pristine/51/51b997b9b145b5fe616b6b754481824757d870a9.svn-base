package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.TeamInformation;
import org.fh.general.ecom.basics.service.TeamInformationService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.TeamInformation.TeamInformationOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.TeamInformationListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.help.TeamInformationListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyy
 * @since 2018-09-20
 */
@RestController
public class TeamInformationController {
    @Autowired
    private TeamInformationService teamInformationService;
    /**
     * 根据id查
     */
    @RequestMapping("TI000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        TeamInformationOutputDTO out=teamInformationService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(out,baseVO);
        if(baseVO!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }
    /**
     * 根据类型查
     */
    @RequestMapping("TI000005")
    public BaseVO selectByTypeId (Long type){
        BaseVO baseVO = new BaseVO();
        List<TeamInformation> out=teamInformationService.selectByTypeId(type);
        if(baseVO!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }
    /**
     * 添加
     * @param dto
     */
    @RequestMapping("TI000002")
    public BaseVO insertCompanyInformation (TeamInformationInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=teamInformationService.insertTeamInformation(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改
     */
    @RequestMapping("TI000003")
    public BaseVO updateCompanyInformation (TeamInformationInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=teamInformationService.updateTeamInformation(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 根据id删除
     */
    @RequestMapping("TI000004")
    public BaseVO deleteByPrimaryKey (Long id){
        BaseVO baseVO=new BaseVO();
        String out=teamInformationService.deleteByPrimaryKey(id);
        if(baseVO!=null){
            baseVO.success();
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 分页列表
     * */
    @RequestMapping("TI000006")
    public PagingVO findPage(TeamInformationListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            TeamInformationListOutputDTO dtoEntity= this.teamInformationService.findPage(dto);
            List<TeamInformationListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;}
            List<TeamInformationListVo> listvo=new ArrayList<TeamInformationListVo>();
            list.forEach((TeamInformationListOutPO temp) -> {
                TeamInformationListVo voEn=new TeamInformationListVo();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });
            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
}
