package org.fh.general.ecom.common.dto.order.goldTicket;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class GoldTicketListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String  ticketSn;
    private String  userNameOrPhone;
    private String  useState;
    private String  offTimeStart;
    private String  offTimeEnd;

}
