package org.fh.general.ecom.common.vo.order.auditLog;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AuditLogListVO {

    private Long id;
    private String objectId;
    private String auditName;
    private String business;
    private String results;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date redTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private String branch;
    private String type;
}
