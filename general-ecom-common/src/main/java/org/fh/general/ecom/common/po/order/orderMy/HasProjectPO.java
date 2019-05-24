package org.fh.general.ecom.common.po.order.orderMy;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class HasProjectPO {

    private Long projectId;
    private String projectName;
    private String projectStatus;
    private String projectStatusName;
    private BigDecimal investMoney;
    private BigDecimal  investReal;
    private String imageUrl;

    List<HasPlanPO> planList;

}
