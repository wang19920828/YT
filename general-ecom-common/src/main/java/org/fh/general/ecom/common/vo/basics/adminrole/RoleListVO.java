package org.fh.general.ecom.common.vo.basics.adminrole;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleListVO {

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

       @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
       private Date createTime;

       private String isDel;

       private Long creatorId;

       private String creatorName;


}
