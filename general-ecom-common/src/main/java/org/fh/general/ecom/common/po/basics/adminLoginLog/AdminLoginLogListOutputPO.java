package org.fh.general.ecom.common.po.basics.adminLoginLog;

import lombok.Data;

import java.util.Date;
@Data
public class AdminLoginLogListOutputPO {

    private Long id;

    private Long adminId;

    private String adminName;

    private String phone;

    private String loginIp;

    private Date loginTime;

    private Date loginOutTime;


}
