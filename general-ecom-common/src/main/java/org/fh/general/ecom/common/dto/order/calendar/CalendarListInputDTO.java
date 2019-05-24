package org.fh.general.ecom.common.dto.order.calendar;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class CalendarListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;


}
