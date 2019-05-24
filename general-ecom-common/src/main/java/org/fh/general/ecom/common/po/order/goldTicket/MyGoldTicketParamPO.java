package org.fh.general.ecom.common.po.order.goldTicket;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class MyGoldTicketParamPO {

    private Long userId;
    private String  useState;
    private String myOffTimeStart;
}
