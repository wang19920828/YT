package org.fh.general.ecom.common.vo.order.memberInvest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPlanListVO {

    private String  orderSn;
    private String  orderType;
    private String  planName;
    private String  num;
    private BigDecimal allPrice;
    private String cycle;
    private BigDecimal minRedRate;;


}
