package org.fh.general.ecom.common.po.order.projectFormula;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectFormulaListOutPO {


    private Long id;
    private Long redProjectId;
    private Long investId;
    private String investName;
    private Long planId;
    private String planName;
    private BigDecimal amountInvest;
    private BigDecimal amountLeiji;
    private BigDecimal benqiPreYearRate;
    private BigDecimal benqiRealYearRate;
    private BigDecimal benqiPreRedAmount;
    private BigDecimal benqiRealRedAmount;
    private String exp1;
    private String exp2;
    private String realShareTime;


}
