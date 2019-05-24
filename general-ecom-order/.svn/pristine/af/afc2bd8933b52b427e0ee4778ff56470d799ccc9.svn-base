package org.fh.general.ecom.order.controller;


import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.order.*;
import org.fh.general.ecom.common.dto.product.order.OrderListCountOutputDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOperUserListDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOrderDetailDTO;
import org.fh.general.ecom.common.dto.product.order.OutputProgrammeCountDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperLogListDTO;
import org.fh.general.ecom.common.enumeration.order.OrderEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.product.order.InputProgrammePO;
import org.fh.general.ecom.common.po.product.order.OrderCountListOutPO;
import org.fh.general.ecom.common.po.product.order.OutputUserCountPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.order.order.OrderListVO;
import org.fh.general.ecom.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-08-09
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;



    /**
     * 分页列表-后台
     * */
    @RequestMapping("ORD8005")
    public PagingVO findPage(OrderListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        OrderListOutputDTO dtoEntity= this.orderService.findPage(dto);
        List<OrderListOutPO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        List<OrderListVO> listvo=new ArrayList<OrderListVO>();
        list.forEach((OrderListOutPO temp) -> {
            OrderListVO voEn=new OrderListVO();
            BeanUtils.copyProperties(temp,voEn);
            voEn.setOrderType(OrderEnum.OrderType.codeOf(temp.getOrderType()).getName());
            voEn.setOrderStatus(OrderEnum.OrderStataus.codeOf(temp.getOrderStatus()).getName());
            voEn.setPayStatus(OrderEnum.PayStatus.codeOf(temp.getPayStatus()).getName());
            listvo.add(voEn);
        });

        pagingVO.success(listvo,dtoEntity.getPageInfo() );
        return  pagingVO;
    }

    /**
     * 导出excel
     * @param dto
     * @return
     */
    @RequestMapping("ORD8006")
    public BaseVO downLoadExcel(OrderListInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try{
            String downpath = this.orderService.downloadExcel(dto);
            if(StringUtils.isNotEmpty(downpath)){
                ExcelOutDTO outDTO=new ExcelOutDTO();
                outDTO.setDownpath(downpath);
                baseVO.success(outDTO);
            }else{
                baseVO.error("导出excel出错");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseVO.exception();
        }
        return baseVO;
    }

    /**
     * 删除
     * */
    @RequestMapping("ORD8002")
    public BaseVO delOrder(Long id){
        BaseVO baseVO=new BaseVO();
        String code=this.orderService.delOrder(id);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return  baseVO;
    }
    /**
     * 修改
     * */
    @RequestMapping("ORD8003")
    public BaseVO updateOrder(OrderUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        String code=this.orderService.updateOrder(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return  baseVO;
    }


    /**
     * 订单详情
     * */
    @RequestMapping("ORD8004")
    public BaseVO detailBgOrder(String orderSn){
        BaseVO baseVO=new BaseVO();
        OrderBgDetailOutputDTO response= this.orderService.detailBgOrder(orderSn);
        if(!OutEnum.SUCCESS.getCode().equals(response.getMsg().getCode())){
            baseVO.setMsg(response.getMsg());
          return baseVO;
        }
        baseVO.success(response);
        return  baseVO;
    }


    /**
     * 修改订单支付状态
     * @param dto
     * @return
     */
    @RequestMapping("BOR8004")
    public BaseVO updatePayStatus(@RequestBody UpdatePayStatusInputDTO dto){
        BaseVO baseVO=new BaseVO();
        String code=this.orderService.updatePayStatus(dto.getOrderSn(),dto.getPayStatus());
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.exception();
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 取消订单（失效）
     * */
    @RequestMapping("ORD8007")
    public BaseVO cancelOrder(CanacelOrderInputDTO dto){
        BaseVO baseVO=new BaseVO();
        String code=this.orderService.cancelOrder(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return  baseVO;
    }

    //产品使用 start
    @RequestMapping("RORD8001")
    public OutputOrderDetailDTO findAppointmentAmountByProjectId(@RequestBody String projectId){
        OutputOrderDetailDTO  response= new  OutputOrderDetailDTO();
        response = this.orderService.findAppointmentAmountByProjectId(projectId);
        return response;
    }

    @RequestMapping("RORD8002")
    public OutputOrderDetailDTO findBySubscribeForAmountProjectId(@RequestBody String projectId){
        OutputOrderDetailDTO  response= new  OutputOrderDetailDTO();
        response = this.orderService.findBySubscribeForAmountProjectId(projectId);
        return response;
    }

    @RequestMapping("RORD8003")
    public PagingExtensionVO findUserListByProjectId(@RequestBody InputProjectOperLogListDTO requestDto){
        PagingExtensionVO pagingVO=new PagingExtensionVO();
        OrderListInputDTO dto = new  OrderListInputDTO();
        dto.setCurrentPageNum(requestDto.getCurrentPageNum());
        dto.setPageCount(requestDto.getPageSize());
        dto.setProjectId(requestDto.getProjectId()+"");
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        List<OrderCountListOutPO> list = new ArrayList<OrderCountListOutPO>();
        OutputOperUserListDTO  responseDto = new OutputOperUserListDTO();
        OrderListCountOutputDTO dtoEntity= this.orderService.findUserListPage(dto);
        if(dtoEntity==null){
            pagingVO.noData();
            return pagingVO;
        }
        list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }

        OutputUserCountPO out = this.orderService.findCountUser(requestDto.getProjectId()+"");
        if(out!=null) {
            BeanUtils.copyProperties(out, responseDto);
        }else{
            responseDto.setRedTotalAmount(BigDecimal.ZERO);
            responseDto.setRegouTotalAmount(BigDecimal.ZERO);
            responseDto.setTotalUserCount(0L);
            responseDto.setYuyueTotalAmount(BigDecimal.ZERO);
        }


        pagingVO.success(list,dtoEntity.getPageInfo(),responseDto);
        return  pagingVO;
    }
    @RequestMapping("RORD8004")
    public OutputOperUserListDTO findCountUser(@RequestBody String projectId){
        OutputOperUserListDTO  responseDto = new OutputOperUserListDTO();
        OutputUserCountPO out = this.orderService.findCountUser(projectId);
        BeanUtils.copyProperties(out,responseDto);
        return responseDto;
    }


    @RequestMapping("RORD8005")
    public OutputProgrammeCountDTO findProgrammeCountById(@RequestBody InputProgrammePO dto){
        OutputProgrammeCountDTO  outputProgrammeCountDTO=new OutputProgrammeCountDTO();
        outputProgrammeCountDTO= this.orderService.findProgrammeCountById(dto);
        return outputProgrammeCountDTO;
    }


//产品使用 end
/**
 * 支付使用 根据单号查订单
 */
    @RequestMapping("ZORD8001")
    public OrderEntityOutDTO findEntityByOrderSnOfPay(@RequestBody String orderSn){
        OrderEntityOutDTO response = this.orderService.findEntityByOrderSn(orderSn);
        return  response;
    }

  /**
     * 会员列表 查询用户是否有投资项目
     */
    @RequestMapping("ZORD8002")
    public Boolean findUserIsNotProduct(@RequestBody Long userId){
        return  this.orderService.findUserIsNotProduct(userId);
    }

}

