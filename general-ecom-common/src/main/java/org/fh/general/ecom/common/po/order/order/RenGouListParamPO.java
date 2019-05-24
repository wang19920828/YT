package org.fh.general.ecom.common.po.order.order;


import lombok.Data;

import java.util.List;

@Data
public class RenGouListParamPO {

    private String orderStauts;

    private String payStatus;

    private String orderType;

    private String purchaseCurrentTime;

    private String oldcodeIsNull;

    private List<String> list;

}
