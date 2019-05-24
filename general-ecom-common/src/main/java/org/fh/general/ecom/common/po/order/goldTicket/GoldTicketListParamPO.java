package org.fh.general.ecom.common.po.order.goldTicket;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.Date;

@Data
public class GoldTicketListParamPO {

    private Integer currentPageNum;
    private Integer pageCount;

    private String  ticketSn;
    private String  userNameOrPhone;
    private String  useState;
    private String  offTimeStart;
    private String  offTimeEnd;
    private Long userId;
    private String myOffTimeStart;
    private Long planId;
}
