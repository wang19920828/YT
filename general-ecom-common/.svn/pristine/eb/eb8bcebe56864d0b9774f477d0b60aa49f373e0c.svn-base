package org.fh.general.ecom.common.po.order.orderMy;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HavePayYuYueEntityPO {

    private BigDecimal investMoney;
    private BigDecimal allPrice;
    private BigDecimal allPriceReal;
    private String payName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date payTime;

}
