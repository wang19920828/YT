package org.fh.general.ecom.common.po.order.memberInvest;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberInvestListOutPO {


    private Long productId;
    private String projectName;
    private Long userId;
    private String userName;
    private String userPhone;
    private String projectStatus;
    private BigDecimal amountAppointment;
    private BigDecimal amountInvest;
    private BigDecimal leijiShareAmount;


}
