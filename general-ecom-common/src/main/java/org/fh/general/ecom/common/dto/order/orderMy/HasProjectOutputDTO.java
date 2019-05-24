package org.fh.general.ecom.common.dto.order.orderMy;


import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.fh.general.ecom.common.base.MessageVO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderMy.HasProjectPO;
import org.fh.general.ecom.common.po.order.orderMy.MyOrderPO;

import java.util.List;

@Data
public class HasProjectOutputDTO {

    List<HasProjectPO> list;

    PageInfo pageInfo;

}
