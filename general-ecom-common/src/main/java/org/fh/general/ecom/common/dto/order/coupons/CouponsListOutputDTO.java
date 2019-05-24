package org.fh.general.ecom.common.dto.order.coupons;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.coupons.CouponsListOutPO;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;

import java.util.List;

@Data
public class CouponsListOutputDTO {

    private List<CouponsListOutPO> list;

    PageInfo pageInfo;
}
