package org.fh.general.ecom.common.po.order.goldTicket;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoldTicketListOutPO {


    private Long id;
    private String ticketSn;
    private BigDecimal amount;
    private Long userId;
    private String userName;
    private String userPhone;
    private Integer delayNum;
    private Date addTime;
    private Date offTime;
    private String useState;
    private String orderSn;
    private String type;
    private String typeName;
    private String branch;
}
