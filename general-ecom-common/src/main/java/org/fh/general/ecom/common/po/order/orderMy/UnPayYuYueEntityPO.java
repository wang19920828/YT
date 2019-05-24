package org.fh.general.ecom.common.po.order.orderMy;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UnPayYuYueEntityPO {

    private BigDecimal investMoney;;//总的预约金额
    private BigDecimal allPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    private Long countdown;//倒计，下单时间超过15分钟未支付则取消，单位毫秒


}
