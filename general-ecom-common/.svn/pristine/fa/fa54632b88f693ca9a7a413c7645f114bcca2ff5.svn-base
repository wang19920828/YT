package org.fh.general.ecom.common.dto.product.organization;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/13
 **/
@Data
public class InputOrganizationDelDTO {

    @Check(empty = false,description = "id")
    private Long  id;
    /**
     * 修改用户
     */
    @Check(empty = false,description = "修改用户")
    public String updateBy;

    /**
     *修改备注
     *
     */
    @Check(empty = false,description = "修改备注")
    public String remarks;
}
