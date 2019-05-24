package org.fh.general.ecom.common.vo.order.calendar;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CalendarListVO {

    private Long id;
    private Long redProjectId;
    private Long planId;
    private String planName;
    private BigDecimal currentRedYearRate;
    private BigDecimal shareAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date shareTime;
    private String exp1;
    private String exp2;

}
