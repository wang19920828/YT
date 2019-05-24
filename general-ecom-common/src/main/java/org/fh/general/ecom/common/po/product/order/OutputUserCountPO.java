package org.fh.general.ecom.common.po.product.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class OutputUserCountPO {

    private Long  totalUserCount;
    private BigDecimal yuyueTotalAmount;
    private BigDecimal regouTotalAmount;
    private BigDecimal yuyueRengouTotalAmount;
    private BigDecimal redTotalAmount;
}
