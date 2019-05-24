package org.fh.general.ecom.common.dto.order.redProject;

import lombok.Data;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogListOutPO;
import org.fh.general.ecom.common.po.order.redProject.RaisePlanPO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RedProjectBgDetailOutputDTO {

    private String projectName;
    private String companyName;
    private String concatUser;
    private String concatPhone;
    private String shareCycle;
    private BigDecimal realRaiseAmount;
    private BigDecimal leijiShareRed;

    //筹资方案及分红计划
    private List<RaisePlanPO> equityList;

    //分红操作日志
    private List<AuditLogListOutPO> logList;





}
