package org.fh.general.ecom.common.dto.order.projectFormula;


import lombok.Data;

@Data
public class SumOnePlanAmountInputDTO {

    private Long planId;

    private String successStatus;

    private  String orderSn;
}
