package org.fh.general.ecom.common.po.order.orderMy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HavePayAppEntityPO {

    //预约支付信息
        private BigDecimal prePrice;
        private BigDecimal preDingJinPriceShould;
        private BigDecimal preDingJinPriceReal;
        private String prePayName;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
        private Date preAddTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
        private Date prePayTime;

    //认购支付信息
        private BigDecimal rgInvestMoney;
        private BigDecimal rgYuyuePrice;
        private BigDecimal rgSharePrice;
        private BigDecimal rgMoneyShould;
        private BigDecimal rgMoneyReal;
        private String rgPayName;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
        private Date rgAddTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
        private Date rgPayTime;



}
