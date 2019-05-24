package org.fh.general.ecom.common.dto.order.memberInvest;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class MemberDetailPageInputDTO {


    @Check(empty = false, description = "项目ID")
    private String productId;
    @Check(empty = false, description = "投资人")
    private String investId;

}
