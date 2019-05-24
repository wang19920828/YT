package org.fh.general.ecom.common.po.order.orderMy;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HasPlanPO {

    private Long planId;
    private String planName;
    private String buyNum;
    private BigDecimal investMoney;

    List<HasPlanPO> childenList;

}
