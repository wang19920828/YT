package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.requestLog.RequestLogListOutPO;
import org.fh.general.ecom.common.vo.order.requestLog.RequestLogDetailVO;
import org.fh.general.ecom.common.vo.order.requestLog.RequestLogListVO;
import org.fh.general.ecom.order.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import org.fh.general.ecom.common.utils.StringUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 请求日志表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@RestController
public class RequestLogController {
    @Autowired
    private RequestLogService requestLogService;


    /**
     * 分页列表
     * */
    @RequestMapping("REQ8005")
    public PagingVO findPage(RequestLogListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            RequestLogListOutputDTO dtoEntity= this.requestLogService.findPage(dto);
            List<RequestLogListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<RequestLogListVO> listvo=new ArrayList<RequestLogListVO>();
            list.forEach((RequestLogListOutPO temp) -> {
                RequestLogListVO voEn=new RequestLogListVO();
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
    @RequestMapping("REQ8001")
    public BaseVO addEntity(RequestLogAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.requestLogService.addEntity(dto);
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

    /**
     * 删除
     * */
    @RequestMapping("REQ8002")
    public BaseVO deleteEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.requestLogService.deleteEntityById(id);
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
    /**
     * 修改
     * */
    @RequestMapping("REQ8003")
    public BaseVO updateEntity(RequestLogUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(dto.getId())){
                baseVO.mustParam();
                return baseVO;
            }
            String code=this.requestLogService.updateEntity(dto);
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
    /**
     * 详情
     * */
    @RequestMapping("REQ8004")
    public BaseVO findEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            RequestLogDetailOutputDTO response= this.requestLogService.findEntityById(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            RequestLogDetailVO vo=new RequestLogDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }

}
