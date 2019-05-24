package org.fh.general.ecom.common.dto.order.redAudit;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SureRedAuditInputDTO {

    @Check(empty = false, description = "关联的主表主键")
    private Long auditId;
    @Check(empty = false, description = "操作人")
    private String auditName ;
    @Check(empty = false, description = "审核状态：1-待审核 2-通过 3-驳回'")
    private String status;
    private String remark;
    @Check(empty = true, description = "审核指定的分红日期（null表示立即发放）")
    private String zhidingTime;
}
