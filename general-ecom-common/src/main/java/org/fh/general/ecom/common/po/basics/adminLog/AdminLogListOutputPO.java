package org.fh.general.ecom.common.po.basics.adminLog;

import lombok.Data;

import java.util.Date;

@Data
public class AdminLogListOutputPO {

    private Long adminLogId;

    private Long adminId;

    private Long roleId;

    private String operModule;

    private String operResult;

    private String remark;

    private Date operTime;
}
