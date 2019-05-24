package org.fh.general.ecom.common.dto.order.auditLog;

import lombok.Data;

import java.util.Date;

@Data
public class AuditLogAddInputDTO {

    private String objectId;
    private String auditName;
    private String business;
    private String results;
    private String remark;
    private String redTime;
    private Date addTime;
    private String branch;
    private String type;
}
