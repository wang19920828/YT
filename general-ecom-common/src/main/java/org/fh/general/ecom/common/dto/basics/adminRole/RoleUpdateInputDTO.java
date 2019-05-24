package org.fh.general.ecom.common.dto.basics.adminRole;

import lombok.Data;

@Data
public class RoleUpdateInputDTO {

    //角色id
    private Long id;
    // 角色名称
    private String roleName;
    // 角色类型
    private String roleType;
    // 备注
    private String remark;

    private String branch;

    private String functions;
}
