package org.fh.general.ecom.common.dto.order.orderLog;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;

import java.util.List;

@Data
public class OrderLogListOutputDTO {

    private List<OrderLogListOutPO> list;

    //PageInfo pageInfo;


}
