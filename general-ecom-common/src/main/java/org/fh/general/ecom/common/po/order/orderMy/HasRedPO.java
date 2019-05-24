package org.fh.general.ecom.common.po.order.orderMy;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HasRedPO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date realShareTime;
    private BigDecimal benqiRealRedAmount;
    private BigDecimal amountLeiji;


}
