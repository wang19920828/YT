package org.fh.general.ecom.common.po.order.couponsCode;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponsCodeListOutPO {
    private Long id;
    private String yhm;
    private BigDecimal amount;
    private String couponName;
    private Long couponsId;
    private String sendType;
    private String situation;
    private Data receiveTime;
    private Date offTime;
    private String status;
    private String recordNo;
    private Long userId;
    private String userName;
    private Date createTime;

}
