package org.fh.general.ecom.common.dto.order.goldTicket;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoldTicketAddInputDTO {

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
    private String branch;
}
