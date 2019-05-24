package org.fh.general.ecom.common.dto.order.dealFlow;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class DealFlowListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;


    private String flowNo;
    /**
     * 订单编号
     */
    private String orderSn;

    private String transType;

    private String addTimeStart;

    private String addTimeEnd;

    private String payType;
}
