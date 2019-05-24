package org.fh.general.ecom.common.vo.order.dealFlow;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DealFlowListVO {
    private Long id;
    private String flowNo;
    private String orderSn;
    private String payType;
    private String transType;
    private String transStuts;
    private BigDecimal transAmt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;

}
