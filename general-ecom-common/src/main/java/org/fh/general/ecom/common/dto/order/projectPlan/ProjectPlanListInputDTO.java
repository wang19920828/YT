package org.fh.general.ecom.common.dto.order.projectPlan;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class ProjectPlanListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;


}
