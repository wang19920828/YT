package org.fh.general.ecom.order.dao;

import org.fh.general.ecom.common.po.order.calendar.InDaysPO;
import org.fh.general.ecom.common.po.order.calendar.InDaysParamPO;
import org.fh.general.ecom.common.po.order.calendar.MyCalendarPO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListParamPO;
import org.fh.general.ecom.order.model.Calendar;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 分红日历表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface CalendarDao extends BaseMapper<Calendar> {

    BigDecimal sumBenqiRealRedAmount(Map<String,Object>  paraMap);

    List<MyCalendarPO> findMyCalendarList(MyCalListParamPO paramPO);

    List<InDaysPO> findInDays(InDaysParamPO paramPO);


}