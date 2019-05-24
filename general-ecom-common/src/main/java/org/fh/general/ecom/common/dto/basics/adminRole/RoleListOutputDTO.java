package org.fh.general.ecom.common.dto.basics.adminRole;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.basics.adminRole.RoleListOutputPO;

import java.util.List;
@Data
public class RoleListOutputDTO {

    private List<RoleListOutputPO> list;

    PageInfo pageInfo;
}
