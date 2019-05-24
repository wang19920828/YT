package org.fh.general.ecom.common.po.basics.adminRole;

import lombok.Data;

import java.util.Date;

@Data
public class RoleListOutputPO {

    //角色id
    private Long id;
    //角色名称
    private String roleName;
    //角色类型
    private String roleType;
    //是否禁用
    private String isDisabled;
    //备注
    private String remark;
    //平台编号
    private String branch;
    //平台名称
    private String branchName;

    //创建时间
    private Date createTime;

    private String isDel;

    private Long creatorId;

    private String creatorName;

}
