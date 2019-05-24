package org.fh.general.ecom.common.dto.order.redProject;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedProjectAddInputDTO {

    private String projectName;
    private String projectId;
    private String companyName;
    private BigDecimal amountReal;
    private BigDecimal amountLeiji;
    private BigDecimal amountExpected;
    private Date currentTime;
    private String overStatus;
    private String shareStatus;
    private Date addTime;
    private String branch;
    private String exp1;
    private String exp2;

}
