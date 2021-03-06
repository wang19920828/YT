package org.fh.general.ecom.common.vo.basics.adminrole;

import lombok.Data;

import java.util.List;

@Data
public class RoleDetailVO {

      private Long id;

      private String roleName;

      private String roleType;

      private String remark;

      private String branch;

      private String branchName;

      private List<ShouquanInitVO> shouquanList;

}
