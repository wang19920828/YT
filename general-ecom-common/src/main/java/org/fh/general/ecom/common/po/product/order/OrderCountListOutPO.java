package org.fh.general.ecom.common.po.product.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class OrderCountListOutPO {

    private long  userId;
    private String projectId;
    private String userName;
    private String userImage;
    private BigDecimal  yuyueAmount;
    private BigDecimal  yuyueRengouAmount;
    private BigDecimal  rengouAmount;
    private String payStatus;
    private String userPhone;

    private BigDecimal redAmount;


}
