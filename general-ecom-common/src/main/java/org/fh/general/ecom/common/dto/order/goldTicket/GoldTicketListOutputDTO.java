package org.fh.general.ecom.common.dto.order.goldTicket;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListOutPO;

import java.util.List;

@Data
public class GoldTicketListOutputDTO {

    private List<GoldTicketListOutPO> list;

    PageInfo pageInfo;
}
