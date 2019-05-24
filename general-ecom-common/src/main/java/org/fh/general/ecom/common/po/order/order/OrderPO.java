package org.fh.general.ecom.common.po.order.order;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class OrderPO {

    private Long orderId;
    private String orderSn;
    private String projectName;
    private BigDecimal investMoney;
    private BigDecimal orgPrice;
    private BigDecimal shareAll;
    private BigDecimal allPrice;
    private Long userId;
    private String userName;
    private String userPhone;
    private String orderStatus;
    private String payStatus;
    private String acceptName;
    private String acceptPhone;
    private String address;
    private String oldcode;
    private String postscript;
    private String payType;
    private String payName;
    private Date addTime;
    private Date payTime;
    private Date completeTime;
    private String orderType;
    private String channel;
    private String isDel;
    private String branch;
    private String branchName;
    private String productId;
    private Long planId;
    private String email;
}
