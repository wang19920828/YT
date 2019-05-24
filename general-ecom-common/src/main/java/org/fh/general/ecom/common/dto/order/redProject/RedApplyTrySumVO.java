package org.fh.general.ecom.common.dto.order.redProject;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class RedApplyTrySumVO {

    private BigDecimal preShareRedTotal;
    private BigDecimal realShareRedTotal;

}
