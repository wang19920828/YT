package org.fh.general.ecom.common.po.order.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CalendarListOutPO {

    private Long id;
    private Long redProjectId;
    private Long planId;
    private String planName;
    private BigDecimal currentRedYearRate;
    private BigDecimal shareAmount;
    private Date shareTime;
    private String exp1;
    private String exp2;


}
