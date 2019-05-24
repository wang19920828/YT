package org.fh.general.ecom.common.dto.order.redProject;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.vo.product.project.PlanNameVO;

import java.util.List;

@Data
public class RedApplyTryInputDTO {
    @Check(empty = false, description = "分红项目主键ID")
    private Long redProjectId;
    @Check(empty = false, description = "试算“方案年化收益率设置”")
    private String planArr;
    @Check(empty = true, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, description = "分页大小")
    private Integer pageCount;
}
