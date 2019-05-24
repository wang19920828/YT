package org.fh.general.ecom.common.po.order.orderMy;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class MyOrderParamPO {



    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    private Long userId;


    private String orderStatus;
    private String payStatus;
    private String orderType;
    private String payStatusMore;

}
