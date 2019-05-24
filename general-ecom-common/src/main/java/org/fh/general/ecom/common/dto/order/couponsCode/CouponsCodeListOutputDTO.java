package org.fh.general.ecom.common.dto.order.couponsCode;


import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.coupons.CouponsListOutPO;
import org.fh.general.ecom.common.po.order.couponsCode.CouponsCodeListOutPO;

import java.util.List;

@Data
public class CouponsCodeListOutputDTO {


    private List<CouponsCodeListOutPO> list;

    PageInfo pageInfo;
}
