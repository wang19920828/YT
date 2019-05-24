package org.fh.general.ecom.common.dto.order.orderProduct;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class OrderProductListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    /**
     * 订单编号
     */
    private String orderSn;

    private String branch;


}
