package org.fh.general.ecom.common.dto.order.calendar;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class FindBgDetailPageInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    @Check(empty = false, description = "分红项目主键")
    private Long redProjectId;

}
