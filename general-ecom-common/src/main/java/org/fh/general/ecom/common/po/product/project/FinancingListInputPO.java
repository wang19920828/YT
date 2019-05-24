package org.fh.general.ecom.common.po.product.project;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;


@Data
public class FinancingListInputPO {

    private String projectStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String purchaseEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String delayDate;




}
