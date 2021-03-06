package org.fh.general.ecom.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.order.order.OrderListParamPO;
import org.fh.general.ecom.common.po.order.order.OrderPO;
import org.fh.general.ecom.common.po.order.order.RenGouListParamPO;
import org.fh.general.ecom.common.po.order.orderMy.HasProjectPO;
import org.fh.general.ecom.common.po.order.orderMy.HasProjectParamPO;
import org.fh.general.ecom.common.po.order.orderMy.MyOrderPO;
import org.fh.general.ecom.common.po.order.orderMy.MyOrderParamPO;
import org.fh.general.ecom.common.po.product.order.InputOrderDetailPO;
import org.fh.general.ecom.common.po.product.order.InputUserCountPO;
import org.fh.general.ecom.common.po.product.order.OrderCountListOutPO;
import org.fh.general.ecom.common.po.product.order.OutputUserCountPO;
import org.fh.general.ecom.order.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 卡产品收费信息表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-08-09
 */
public interface OrderDao extends BaseMapper<Order> {

    List<OrderListOutPO> findOrderList(OrderListParamPO paramPO);

    InputOrderDetailPO findAmountByProjectId(InputOrderDetailPO inputOrderDetailPO);

    List<OrderCountListOutPO> findUserListPage(OrderCountListOutPO paramPO);

    OutputUserCountPO findCountUser(InputUserCountPO inputUserCountPO);

    BigDecimal sumAllPriceByProjectId(Map<String, Object> param);

    void updateOrderStatus(Map<String, Object> param);

    List<MyOrderPO> findMyOrderPage(MyOrderParamPO paramPO);

    List<HasProjectPO> findHasProjectPage(HasProjectParamPO paramPO);

    //List<Order> findRenGouQiOrderList(RenGouListParamPO paramPO);
}