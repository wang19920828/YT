package org.fh.general.ecom.common.dto.order.calendar;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.calendar.CalendarListOutPO;

import java.util.List;

@Data
public class CalendarListOutputDTO {


    private List<CalendarListOutPO> list;

    PageInfo pageInfo;

}
