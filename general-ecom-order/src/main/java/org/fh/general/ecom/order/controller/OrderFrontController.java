package org.fh.general.ecom.order.controller;


import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.orderFront.AppointmentAddOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.AppointmentDetailInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.RenGouAddOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.RenGouDetailInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.DetailHasProjectInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.DetailHasProjectOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HasProjectInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HasProjectOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HavePayOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HavePayOrderOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.MyOrderPageInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.MyOrderPageOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.UnPayOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.UnPayOrderOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderMy.MyOrderPO;
import org.fh.general.ecom.order.service.OrderFrontService;
import org.fh.general.ecom.order.service.OrderMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * 订单前台相关接口
 */
@RestController
public class OrderFrontController {

    @Autowired
    private OrderFrontService orderFrontService;
    @Autowired
    private OrderMyService orderMyService;

    /**
     * 预约结算页
     * @param dto
     * @return
     */
    @RequestMapping("FRO8001")
    public BaseVO appointmentPayInfo(AppointmentDetailInputDTO dto){
        return this.orderFrontService.appointmentPayInfo(dto);
    }

    /**
     * 预约结算-支付
     * @param dto
     * @return
     */
   @RequestMapping("FRO8002")
    public BaseVO appointmentAddOrder(AppointmentAddOrderInputDTO dto){
        return this.orderFrontService.appointmentAddOrder(dto);
    }


    /**
     * 认购结算页
     * @param dto
     * @return
     */
    @RequestMapping("FRO8003")
   public BaseVO renGouPayInfo(RenGouDetailInputDTO dto){
        return this.orderFrontService.renGouPayInfo(dto);
    }

    /**
     * 认购结束算-支付
     * @param dto
     * @return
     */
     @RequestMapping("FRO8004")
    public BaseVO renGouAddOrder(RenGouAddOrderInputDTO dto){
        return this.orderFrontService.renGouAddOrder(dto);
    }

    /**
     * 我的订单列表(前台)
     * @param dto
     * @return
     */
    @RequestMapping("FRO8005")
    public PagingVO findMyOrderPage(MyOrderPageInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        MyOrderPageOutputDTO resDto=this.orderMyService.findMyOrderPage(dto);
        if(resDto==null){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(resDto.getList(),resDto.getPageInfo());
        return pagingVO;
    }

    /**
     * 未支付订单详情页面
     * @param dto
     * @return
     */
    @RequestMapping("FRO8006")
    public BaseVO findUnPayOrderDetail(UnPayOrderInputDTO dto){
        BaseVO baseVO=new BaseVO();
        UnPayOrderOutputDTO resDto=this.orderMyService.findUnPayOrderDetail(dto);
        if(!resDto.getMsg().getCode().equals(OutEnum.SUCCESS.getCode())){
            baseVO.setMsg(resDto.getMsg());
            return baseVO;
        }
        baseVO.success(resDto);
        return baseVO;
    }


    /**
     * 已支付订单详情页面
     * @param dto
     * @return
     */
    @RequestMapping("FRO8007")
    public BaseVO findHavePayOrderDetail(HavePayOrderInputDTO dto){
        BaseVO baseVO=new BaseVO();
        HavePayOrderOutputDTO resDto=this.orderMyService.findHavePayOrderDetail(dto);
        if(!resDto.getMsg().getCode().equals(OutEnum.SUCCESS.getCode())){
            baseVO.setMsg(resDto.getMsg());
            return baseVO;
        }
        baseVO.success(resDto);
        return baseVO;
    }


    /**
     * 个人中心-我的已投项目
     * @param dto
     * @return
     */
    @RequestMapping("FRO8008")
    public BaseVO findHasProjectPage(HasProjectInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        HasProjectOutputDTO resDto= this.orderMyService.findHasProjectPage(dto);
        if(resDto==null){
            baseVO.noData();
            return baseVO;
        }
        baseVO.success(resDto);
        return baseVO;
    }

    /**
     * 个人中心-某个已投项目详情
     * @param dto
     * @return
     */
    @RequestMapping("FRO8009")
    public BaseVO detailHasProject(DetailHasProjectInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        DetailHasProjectOutputDTO resDto= this.orderMyService.detailHasProject(dto);
        if(resDto==null){
            baseVO.exception();
            return baseVO;
        }
        baseVO.success(resDto);
        return baseVO;
    }
}
