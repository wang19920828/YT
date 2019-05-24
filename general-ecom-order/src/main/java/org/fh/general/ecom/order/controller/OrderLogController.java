package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;
import org.fh.general.ecom.common.vo.order.orderLog.OrderLogDetailVO;
import org.fh.general.ecom.common.vo.order.orderLog.OrderLogListVO;
import org.fh.general.ecom.order.service.OrderLogService;
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
 * 订单操作日志 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@RestController
public class OrderLogController {
    @Autowired
    private OrderLogService orderLogService;


    /**
     * 分页列表
     * */
    @RequestMapping("OLO8005")
    public PagingVO findPage(OrderLogListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        /*try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            OrderLogListOutputDTO dtoEntity= this.orderLogService.findPage(dto);
            List<OrderLogListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<OrderLogListVO> listvo=new ArrayList<OrderLogListVO>();
            list.forEach((OrderLogListOutPO temp) -> {
                OrderLogListVO voEn=new OrderLogListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return  pagingVO;
    }



    /**
     * 添加
     * */
    @RequestMapping("OLO8001")
    public BaseVO addEntity(OrderLogAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.orderLogService.addEntity(dto);
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
    @RequestMapping("OLO8002")
    public BaseVO deleteEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.orderLogService.deleteEntityById(id);
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
    @RequestMapping("OLO8003")
    public BaseVO updateEntity(OrderLogUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(dto.getId())){
                baseVO.mustParam();
                return baseVO;
            }
            String code=this.orderLogService.updateEntity(dto);
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
    @RequestMapping("OLO8004")
    public BaseVO findEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            OrderLogDetailOutputDTO response= this.orderLogService.findEntityById(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            OrderLogDetailVO vo=new OrderLogDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }
}
