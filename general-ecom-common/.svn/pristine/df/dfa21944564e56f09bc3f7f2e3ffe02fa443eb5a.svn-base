package org.fh.general.ecom.common.vo.order.orderLog;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderLogDetailVO {

    private Long id;
    /**
     * 子订单编号
     */
    private String orderSn;
    /**
     * 订单状态
     */
    private String orderStatusName;
    /**
     * 付款状态
     */
    private String payStatusName;
    /**
     * 发货状态
     */
    private String shippingStatusName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 处理人姓名
     */
    private String editor;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date editDate;
    private String title;
    private BigDecimal amount;



}
