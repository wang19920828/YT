package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductAddInputDTO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductListInputDTO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductListOutputDTO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderProduct.OrderProductListOutPO;
import org.fh.general.ecom.common.vo.order.orderProduct.OrderProductDetailVO;
import org.fh.general.ecom.common.vo.order.orderProduct.OrderProductListVO;
import org.fh.general.ecom.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import org.fh.general.ecom.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单产品关系表
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@RestController
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;


    /**
     * 分页列表
     * */
    @RequestMapping("OPC8005")
    public PagingVO findPage(OrderProductListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            OrderProductListOutputDTO dtoEntity= this.orderProductService.findPage(dto);
            List<OrderProductListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<OrderProductListVO> listvo=new ArrayList<OrderProductListVO>();
            list.forEach((OrderProductListOutPO temp) -> {
                OrderProductListVO voEn=new OrderProductListVO();
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
    @RequestMapping("OPC8001")
    public BaseVO addEntity(OrderProductAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.orderProductService.addEntity(dto);
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
    @RequestMapping("OPC8002")
    public BaseVO deleteEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.orderProductService.deleteEntityById(id);
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
    @RequestMapping("OPC8003")
    public BaseVO updateEntity(OrderProductUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(dto.getId())){
                baseVO.mustParam();
                return baseVO;
            }
            String code=this.orderProductService.updateEntity(dto);
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
    @RequestMapping("OPC8004")
    public BaseVO findEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            OrderProductDetailOutputDTO response= this.orderProductService.findEntityById(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            OrderProductDetailVO vo=new OrderProductDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }

}
