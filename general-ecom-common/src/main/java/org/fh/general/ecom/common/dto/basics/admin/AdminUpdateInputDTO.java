package org.fh.general.ecom.common.dto.basics.admin;

import lombok.Data;

@Data
public class AdminUpdateInputDTO {
    // 管理员id
    private Long adminId;
    // 登陆名
    private String name;
    //真实姓名
    private String realName;
    //手机号
    private String adminPhone;
    //邮箱
    private String adminEmail;
    // 备注
    private String remark;
    // 角色id
    private String roleId;
    //渠道
    private String channel;
    //平台
    private String branch;
}
