package org.fh.general.ecom.common.dto.order.order;

import lombok.Data;

@Data
public class UpdatePayStatusInputDTO {

    private String orderSn;

    private String payStatus;
}
