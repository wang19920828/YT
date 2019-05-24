package org.fh.general.ecom.common.vo.order.order;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogPO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class DetailSaleOrderVO {

    private Long orderId;
    private String orderSn;
    private String projectName;
    private BigDecimal orgPrice;
    private BigDecimal shareAll;
    private BigDecimal allPrice;
    private Long userId;
    private String orderStatus;
    private String shippingStatus;
    private String payStatus;
    private String acceptName;
    private String tel;
    private String address;
    private String oldcode;
    private String lat;
    private String lon;
    private String postscript;
    private String isSend;
    private String payType;
    private String payName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date payTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date shippingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date completeTime;
    private String platform;
    private String channel;
    private String isDel;
    private String branch;
    private String branchName;

}
