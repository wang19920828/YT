package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.calendar.CalendarAddInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.CalendarListInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.CalendarListOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.DetailCalendarHeadInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.DetailCalendarHeadOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgDetailPageInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgDetailPageOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgFormulaPageInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgFormulaPageOutputDTO;
import org.fh.general.ecom.common.po.order.calendar.InDaysInputDTO;
import org.fh.general.ecom.common.po.order.calendar.InDaysOutputDTO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListInputDTO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListOutputDTO;
import org.fh.general.ecom.order.model.Calendar;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 分红日历表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface CalendarService extends IService<Calendar> {


    CalendarListOutputDTO findPage(CalendarListInputDTO dto)throws Exception;


    String addEntity(Long redProjectId)throws Exception;

    FindBgDetailPageOutputDTO findBgDetailPage(FindBgDetailPageInputDTO dto) throws Exception;

    FindBgFormulaPageOutputDTO findBgFormulaPage(FindBgFormulaPageInputDTO dto);

    DetailCalendarHeadOutputDTO calendarHead(DetailCalendarHeadInputDTO dto);

    MyCalListOutputDTO findMyCalendarList(MyCalListInputDTO dto);

    InDaysOutputDTO findInDays(InDaysInputDTO inputDTO);

}
