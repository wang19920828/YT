package org.fh.general.ecom.common.po.order.redAudit;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedAuditListOutPO {

    private Long id;
    private Long redProjectId;
    private String projectName;
    private String companyName;
    private String projectId;
    private Date currentTime;
    private String zhidingTime;
    private BigDecimal currentAmount;
    private String status;
    private Date addTime;
    private String userName;
    private String branch;
    private String exp1;
    private String exp2;


}
