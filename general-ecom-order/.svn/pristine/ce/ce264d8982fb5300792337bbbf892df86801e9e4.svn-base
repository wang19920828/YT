package org.fh.general.ecom.order.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.order.orderFront.AppointmentAddOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.AppointmentDetailInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.RenGouAddOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderFront.RenGouDetailInputDTO;
import org.fh.general.ecom.order.model.Order;

public interface OrderFrontService extends IService<Order> {

     BaseVO appointmentPayInfo(AppointmentDetailInputDTO dto);

     BaseVO appointmentAddOrder(AppointmentAddOrderInputDTO dto);

     BaseVO  renGouPayInfo(RenGouDetailInputDTO dto);

     BaseVO  renGouAddOrder(RenGouAddOrderInputDTO dto);
}
