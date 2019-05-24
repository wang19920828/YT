package org.fh.general.ecom.common.dto.order.orderFront;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class YuyuePriceOutDTO {

    private BigDecimal yuyePrice;
    private String orderSn;
}
