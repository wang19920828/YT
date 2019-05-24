package org.fh.general.ecom.order.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.order.orderProduct.*;
import org.fh.general.ecom.common.dto.product.order.OutputProgrammeCountDTO;
import org.fh.general.ecom.common.po.order.orderMy.HasPlanChildenPO;
import org.fh.general.ecom.common.po.order.orderMy.HasPlanPO;
import org.fh.general.ecom.common.po.order.orderProduct.OpcountPO;
import org.fh.general.ecom.common.po.order.orderProduct.OrderProductPO;
import org.fh.general.ecom.common.po.order.redProject.RaisePlanPO;
import org.fh.general.ecom.common.po.product.order.InputProgrammePO;
import org.fh.general.ecom.order.model.OrderProduct;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单产品关系表
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
public interface OrderProductService extends IService<OrderProduct> {

    String addEntity(OrderProductAddInputDTO dto)throws Exception;

    String deleteEntityById(Long id)throws Exception;

    String updateEntity(OrderProductUpdateInputDTO dto)throws Exception;

    OrderProductDetailOutputDTO findEntityById(Long id)throws Exception;

    OrderProductListOutputDTO findPage(OrderProductListInputDTO dto)throws Exception;

    List<OrderProductPO> findOpListByOrderSn(String orderSn);

    RaisePlanPO getOpCount(OpcountPO param);

    OrderProductDTO findEntityByOrderSn(String orderSn);

    OutputProgrammeCountDTO findProgrammeCountById(InputProgrammePO dto );

    List<OrderProduct>  findOpListByProjectId(Long userId,Long projectId);

    List<HasPlanPO> listSumPlan(Map<String,Object> map);

}
