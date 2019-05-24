package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditAddInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.SureRedAuditInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectBgDetailOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.redAudit.RedAuditListOutPO;
import org.fh.general.ecom.common.vo.order.redAudit.RedAuditBgDetailVO;
import org.fh.general.ecom.common.vo.order.redAudit.RedAuditListVO;
import org.fh.general.ecom.common.vo.order.redProject.RedProjectBgDetailVO;
import org.fh.general.ecom.order.service.RedAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分红审核表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class RedAuditController {





    @Autowired
    private RedAuditService redAuditService;


    /**
     * 分页列表
     * */
    @RequestMapping("REA8005")
    public PagingVO findPage(RedAuditListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            RedAuditListOutputDTO dtoEntity= this.redAuditService.findPage(dto);
            List<RedAuditListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<RedAuditListVO> listvo=new ArrayList<RedAuditListVO>();
            list.forEach((RedAuditListOutPO temp) -> {
                RedAuditListVO voEn=new RedAuditListVO();
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
     * 分红审核详情
     * auditId  对应tb_red_audit主键
     * */
    @RequestMapping("REA8002")
    public BaseVO detailBgRedAudit(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            RedAuditBgDetailOutputDTO response= this.redAuditService.detailBgRedAudit(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            RedAuditBgDetailVO vo=new RedAuditBgDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            baseVO.exception();
            e.printStackTrace();
        }
        return  baseVO;
    }


    /**
     * 分红审核-确定按钮
     * */
    @RequestMapping("REA8003")
    public BaseVO sureRedAudit(SureRedAuditInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            String code= this.redAuditService.sureRedAudit(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            baseVO.exception();
            e.printStackTrace();
        }
        return  baseVO;
    }





}
