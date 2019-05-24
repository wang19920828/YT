package org.fh.general.ecom.common.vo.order.redAudit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogShowPO;
import org.fh.general.ecom.common.po.order.projectFormula.ProjectFormulaShowPO;
import org.fh.general.ecom.common.po.order.projectPlan.ProjectPlanShowPO;

import java.util.Date;
import java.util.List;

@Data
public class RedAuditBgDetailVO {


    private String projectName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date currentTime;

    //方案年化收益率
    List<ProjectPlanShowPO> planList;
    //分红试算列表
    List<ProjectFormulaShowPO> formulaList;
    //审核日志
    List<AuditLogShowPO> auditLogList;

}
