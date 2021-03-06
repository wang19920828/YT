package org.fh.general.ecom.common.vo.order.coupons;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponsListVO {


    private Long id;
    private String couponName;
    private Long couponsId;
    private String type;
    private Long sendNum;
    private Long receiveNum;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private BigDecimal diMoney;
    private BigDecimal lowMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date receiveStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date receiveEnd;
    private Long preSendNum;
    private Long limitNum;
    private String rule;
    private String image;
    private Long days;
    private String branch;
}
