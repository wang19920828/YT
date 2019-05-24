package org.fh.general.ecom.common.po.order.projectFormula;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectFormulaShowPO {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date realShareTime;
}
