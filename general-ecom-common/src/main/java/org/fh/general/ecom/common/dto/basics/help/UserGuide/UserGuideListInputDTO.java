package org.fh.general.ecom.common.dto.basics.help.UserGuide;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.Date;

@Data
public class UserGuideListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    private Date publishTimes;
    private  String type;
    private  String startTime;
    private  String endTime;
}
