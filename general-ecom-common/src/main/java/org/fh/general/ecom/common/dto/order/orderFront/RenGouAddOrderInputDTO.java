package org.fh.general.ecom.common.dto.order.orderFront;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RenGouAddOrderInputDTO {

    private Long userId;
    private Long planId;
    private Long projectId;
    private Long buyNum;
    private Long addressId;
    private BigDecimal allPrice;
    private String ticketSn;
    private String yhm;
    private String email;
    //订单类型: 0-预约 1-预约认购 2-公开认购
    private String orderType;
    //订单来源:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
    private String channel;

    private Long scores=0L;

}
