package org.fh.general.ecom.common.po.order.orderMy;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class HasPlanChildenPO {

    private Long planId;
    private String planName;
    private String buyNum;
    private BigDecimal investMoney;

}
