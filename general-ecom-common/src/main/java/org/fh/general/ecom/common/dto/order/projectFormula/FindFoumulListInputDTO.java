package org.fh.general.ecom.common.dto.order.projectFormula;

import lombok.Data;

import java.util.Date;

@Data
public class FindFoumulListInputDTO {

    private String shareStatus;
    private String auditStatus;
    private Date preShareTimeEnd;

    private Long investId;
    private Long productId;


}
