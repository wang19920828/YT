package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectAddFinancingDTO {

    @Check(empty = true,description ="项目id")
    private Long projectId;

    @Check(empty = true,description ="持股公司")
    private String holdingCompany;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="项目估值")
    private BigDecimal projectValuation;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="拟众筹金额")
    private BigDecimal totalAmount;

    @Check(empty = true,description ="是否可超募")
    private String isOverRaised;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="超募后金额")
    private BigDecimal maxAmount;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="成功募资比例")
    private BigDecimal successRate;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="分红周期")
    private String redTerm;

    @Check(empty = true,regexType = RegexType.DECIMAL,description="起投金额")
    private BigDecimal InvestmentAmount;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="限投金额")
    private BigDecimal limitAmount;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="投资期限")
    private String investmentTerm;
    /**
     * 提现退出天数-预留
     */
    @Check(empty = true,regexType = RegexType.DECIMAL,description ="提现退出天数")
    private BigDecimal withdrawalDays;

    @Check(empty = true,description ="预约开始时间")
    private String startTime;

    @Check(empty = true,description ="预约结束时间")
    private String endTime;

    @Check(empty = true,description ="申购开始时间")
    private String purchaseStartTime;

    @Check(empty = true,description ="申购结束时间")
    private String purchaseEndTime;
    /**
     * 是否延期
     */
    @Check(empty = true,description ="是否延期")
    private String isDelay;
    /**
     * 延期截止时间
     */
    @Check(empty = true,description ="延期截止时间")
    private String delayDate;
    /**
     * 创建人
     */
    @Check(empty = true,description ="创建人")
    private String createBy;


    private String updateBy;

    private String branch;

    private String branchName;

    private String projectStatus;
}
