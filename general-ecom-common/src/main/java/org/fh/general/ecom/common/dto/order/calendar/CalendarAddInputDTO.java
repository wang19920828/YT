package org.fh.general.ecom.common.dto.order.calendar;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CalendarAddInputDTO {

    private Long redProjectId;
    private Long planId;
    private String planName;
    private BigDecimal currentRedYearRate;
    private BigDecimal shareAmount;
    private Date shareTime;
    private String exp1;
    private String exp2;



}
