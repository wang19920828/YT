package org.fh.general.ecom.common.dto.basics.admin;


import lombok.Data;

@Data
public class AdminLoginDTO {

    private String browserCode;
    //管理用户名
    private String name;
    //登陆密码
    private String loginPwd;
    //登陆ip
    private String loginIp;

}
