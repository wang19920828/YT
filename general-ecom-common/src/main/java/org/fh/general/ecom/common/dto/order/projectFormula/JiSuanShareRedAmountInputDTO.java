package org.fh.general.ecom.common.dto.order.projectFormula;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class JiSuanShareRedAmountInputDTO {

    private BigDecimal allPrice;
    private BigDecimal yearRate;
    private BigDecimal cycle;
}
