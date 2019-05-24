package org.fh.general.ecom.common.dto.basics.pay;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/11 17:42
 * @Description:
 */
@Data
public class WxApplyRefundInputDTO {
    private String channelNo;
    private String orderSn;
    private BigDecimal refundAmount;
    private String branch;
}
