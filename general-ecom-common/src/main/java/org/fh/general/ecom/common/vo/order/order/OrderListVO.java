package org.fh.general.ecom.common.vo.order.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderListVO {

    private Long orderId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private String orderSn;
    private String  orderType;
    private String orderStatus;
    private String yuyuePrice;
    private String  allPrice;
    private String  projectName;
    private String  userName;
    private String userPhone;
    private String payStatus;

}
