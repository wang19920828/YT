package org.fh.general.ecom.order.dao;

import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListOutPO;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListParamPO;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.order.order.OrderListParamPO;
import org.fh.general.ecom.order.model.GoldTicket;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 代金券表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface GoldTicketDao extends BaseMapper<GoldTicket> {

    List<GoldTicketListOutPO> findList(GoldTicketListParamPO paramPO);

}