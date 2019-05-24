package org.fh.general.ecom.common.po.order.calendar;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MyCalendarPO {

    private Long projectId;
    private String projectName;
    private String projectStatus;

    private List<MyCalPlanPO> planList;

    private BigDecimal total;
}
