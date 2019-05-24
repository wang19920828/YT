package org.fh.general.ecom.common.dto.order.redAudit;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogShowPO;
import org.fh.general.ecom.common.po.order.projectFormula.ProjectFormulaShowPO;
import org.fh.general.ecom.common.po.order.projectPlan.ProjectPlanShowPO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RedAuditBgDetailOutputDTO {


    private String projectName;
    private String currentTime;//2018-10-19

    private BigDecimal totalPreRedAmount;//预期分红金额合计
    private BigDecimal totalRealRedAmount;//实际分红金额合计

    //方案年化收益率
    List<ProjectPlanShowPO> planList;
    //分红试算列表
    List<ProjectFormulaShowPO> formulaList;
    //审核日志
    List<AuditLogShowPO> auditLogList;
}
