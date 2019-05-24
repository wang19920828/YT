package org.fh.general.ecom.common.po.product.project;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class RenGouQiParamPO {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String purchaseStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String purchaseEndTime;
    //项目状态列表
    private List<String> list;

}
