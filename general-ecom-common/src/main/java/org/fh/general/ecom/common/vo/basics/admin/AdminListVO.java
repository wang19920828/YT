package org.fh.general.ecom.common.vo.basics.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdminListVO {

    //管理员id
    private Long adminId;
    //管理用户名
    private String name;
    //真实姓名
    private String  realName;
    //手机号
    private String adminPhone;
    //邮箱
    private String adminEmail;
    //用户状态
    private String status;
    //备注
    private String remark;
    // 角色名称
    private String roleNames;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private Date createTime;

}
