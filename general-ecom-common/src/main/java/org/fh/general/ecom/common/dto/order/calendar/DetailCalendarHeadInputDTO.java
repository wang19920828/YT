package org.fh.general.ecom.common.dto.order.calendar;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class DetailCalendarHeadInputDTO {

    @Check(empty = false, description = "分红项目主键")
    private Long redProjectId;
}
