package org.fh.general.ecom.common.vo.basics.adminLoginLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdminLoginLogListVO {

    private Long id;

    private Long adminId;

    private String adminName;

    private String phone;

    private String loginIp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date loginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date loginOutTime;


}
