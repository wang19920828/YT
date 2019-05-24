package org.fh.general.ecom.common.dto.order.orderProduct;


import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.orderProduct.OrderProductListOutPO;

import java.util.List;

@Data
public class OrderProductListOutputDTO {

    private List<OrderProductListOutPO> list;

    PageInfo pageInfo;

}
