package org.fh.general.ecom.common.dto.order.orderMy;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class MyOrderPageInputDTO {


    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private Long userId;
    //1-待支付订单 2-成功订单 3-退款订单 4-失效订单
    @Check(empty = false, description = "订单类型")
    private String typeFlag;

}
