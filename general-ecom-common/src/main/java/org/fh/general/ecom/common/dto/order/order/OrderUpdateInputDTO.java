package org.fh.general.ecom.common.dto.order.order;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class OrderUpdateInputDTO {

    @Check(empty = false, description = "订单编号")
    private String orderSn;

    private String acceptName;



}
