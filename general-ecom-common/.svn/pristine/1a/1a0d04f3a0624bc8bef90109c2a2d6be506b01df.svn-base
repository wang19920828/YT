package org.fh.general.ecom.common.po.order.memberInvest;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class MemberInvestListParamPO {


    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String userNameOrPhone;
    private String projectName;
    private String projectStatus;

}
