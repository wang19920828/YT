package org.fh.general.ecom.order.service;

import com.baomidou.mybatisplus.service.IService;
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
import org.fh.general.ecom.order.model.Order;

public interface OrderMyService extends IService<Order> {

     MyOrderPageOutputDTO findMyOrderPage(MyOrderPageInputDTO dto);

     UnPayOrderOutputDTO findUnPayOrderDetail(UnPayOrderInputDTO dto);

     HavePayOrderOutputDTO findHavePayOrderDetail(HavePayOrderInputDTO dto);

     HasProjectOutputDTO findHasProjectPage(HasProjectInputDTO dto);

     DetailHasProjectOutputDTO detailHasProject(DetailHasProjectInputDTO dto);

}
