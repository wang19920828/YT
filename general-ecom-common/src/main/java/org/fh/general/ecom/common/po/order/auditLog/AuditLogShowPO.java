package org.fh.general.ecom.common.po.order.auditLog;

import lombok.Data;

import java.util.Date;


@Data
public class AuditLogShowPO {

    private Long id;
    private String objectId;
    private String auditName;
    private String business;
    private String results;
    private String remark;
    private Date redTime;
    private Date addTime;
    private String branch;
    private String type;


}
