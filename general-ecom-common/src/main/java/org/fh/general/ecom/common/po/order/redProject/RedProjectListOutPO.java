package org.fh.general.ecom.common.po.order.redProject;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedProjectListOutPO {

    private Long id;
    private String projectName;
    private String projectId;
    private Long companyId;
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
