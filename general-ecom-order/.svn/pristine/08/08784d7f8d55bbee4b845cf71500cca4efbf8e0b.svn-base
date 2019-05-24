package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.orderLog.OrderLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogUpdateInputDTO;
import org.fh.general.ecom.order.model.OrderLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单操作日志 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
public interface OrderLogService extends IService<OrderLog> {
    String addEntity(OrderLogAddInputDTO dto)throws Exception;

    String deleteEntityById(Long id)throws Exception;

    String updateEntity(OrderLogUpdateInputDTO dto)throws Exception;

    OrderLogDetailOutputDTO findEntityById(Long id)throws Exception;

    OrderLogListOutputDTO findPage(OrderLogListInputDTO dto)throws Exception;

    OrderLogListOutputDTO findListByOrderSn(String orderSn) throws  Exception;

}
