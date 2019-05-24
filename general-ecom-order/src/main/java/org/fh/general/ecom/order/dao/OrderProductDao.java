package org.fh.general.ecom.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.fh.general.ecom.common.po.order.orderMy.HasPlanChildenPO;
import org.fh.general.ecom.common.po.order.orderMy.HasPlanPO;
import org.fh.general.ecom.common.po.order.orderProduct.ListSumPlanParamPO;
import org.fh.general.ecom.common.po.order.orderProduct.OpcountPO;
import org.fh.general.ecom.common.po.order.redProject.RaisePlanPO;
import org.fh.general.ecom.order.model.OrderProduct;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 订单产品关系表
dis_money=(one_money-less_money)*num;
shifu_total（实付总价）=yuanjia_total（原总价）-dis_money(促销优惠)-avgAll（平摊优惠）;
shifu_unit_money=shifu_total/num； Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
public interface OrderProductDao extends BaseMapper<OrderProduct> {

    RaisePlanPO getOPCount(OpcountPO paramPO);

    OrderProduct findProgrammeCountById(Map<String,Object> map);

    List<HasPlanPO> listSumPlan(ListSumPlanParamPO paramPO);

}