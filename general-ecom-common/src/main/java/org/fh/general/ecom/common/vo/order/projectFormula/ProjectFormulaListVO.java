package org.fh.general.ecom.common.vo.order.projectFormula;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectFormulaListVO {

    private Long id;
    private Long redProjectId;
    private Long productId;
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
    private String orderSn;
    private Date preShareTime;
    private String accountNo;
    private Date addTime;
    private Long numbers;
    private Long allNumbers;

}
