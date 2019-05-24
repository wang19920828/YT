package org.fh.general.ecom.common.dto.order.redProject;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class RedApplySubmitInputDTO {

    @Check(empty = false, description = "分红项目主键ID")
    private Long redProjectId;
    @Check(empty = false, description = "试算“方案年化收益率设置”")
    private String planArr;
}
