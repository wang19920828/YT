package org.fh.general.ecom.common.dto.product.project;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FinancingListInputDTO {

    private String projectStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String purchaseEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String delayDate;
}
