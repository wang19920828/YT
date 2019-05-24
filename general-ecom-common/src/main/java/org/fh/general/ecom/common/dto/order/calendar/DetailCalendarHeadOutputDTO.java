package org.fh.general.ecom.common.dto.order.calendar;


import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.fh.general.ecom.common.base.Entity;
import org.fh.general.ecom.common.base.MessageVO;
import org.fh.general.ecom.common.base.PageVO;
import org.fh.general.ecom.common.comm.SpecailPage;
import org.fh.general.ecom.common.enums.OutEnum;

import java.math.BigDecimal;

@Data
public class DetailCalendarHeadOutputDTO {

    private String projectName;
    private String companyName;
    private String concatUser;
    private String concatPhone;

    private BigDecimal sumTotalRealRedAmount;





}
