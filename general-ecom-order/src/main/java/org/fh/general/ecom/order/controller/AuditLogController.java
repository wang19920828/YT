package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.order.auditLog.AuditLogListVO;
import org.fh.general.ecom.order.service.AuditLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分红审核日志表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */

@RestController
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;


    /**
     * 分页列表
     * */
    @RequestMapping("AUD8005")
    public PagingVO findPage(AuditLogListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            AuditLogListOutputDTO dtoEntity= this.auditLogService.findPage(dto);
            List<AuditLogListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<AuditLogListVO> listvo=new ArrayList<AuditLogListVO>();
            list.forEach((AuditLogListOutPO temp) -> {
                AuditLogListVO voEn=new AuditLogListVO();
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
     * 添加
     * */
    @RequestMapping("AUD8001")
    public BaseVO addEntity(AuditLogAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.auditLogService.addEntity(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }



}
