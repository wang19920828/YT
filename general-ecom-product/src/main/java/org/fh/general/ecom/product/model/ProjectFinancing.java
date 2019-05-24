package org.fh.general.ecom.product.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 项目筹资信息
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
@Data
@TableName("tb_project_financing")
public class ProjectFinancing implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 持股公司--因原型修改作废
     */
	@TableField("holding_company")
	private String holdingCompany;
    /**
     * 项目估值
     */
	@TableField("project_valuation")
	private BigDecimal projectValuation;
    /**
     * 拟众筹金额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
    /**
     * 是否可超募
     */
	@TableField("is_over_raised")
	private String isOverRaised;
    /**
     * 超募后金额
     */
	@TableField("max_amount")
	private BigDecimal maxAmount;
    /**
     * 成功募资比例  80% 存成80
     */
	@TableField("success_rate")
	private BigDecimal successRate;
    /**
     * 分红周期：日，月，年
     */
	@TableField("red_term")
	private String redTerm;
    /**
     * 起投金额
     */
	@TableField("Investment_amount")
	private BigDecimal InvestmentAmount;
    /**
     * 限投金额
     */
	@TableField("limit_amount")
	private BigDecimal limitAmount;
    /**
     * 投资期限-预留
     */
	@TableField("investment_term")
	private String investmentTerm;
    /**
     * 提现退出天数-预留
     */
	@TableField("withdrawal_days")
	private BigDecimal withdrawalDays;
    /**
     * 预约开始时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 预约结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 申购开始时间
     */
	@TableField("purchase_start_time")
	private Date purchaseStartTime;
    /**
     * 申购结束时间
     */
	@TableField("purchase_end_time")
	private Date purchaseEndTime;
    /**
     * 是否延期
     */
	@TableField("is_delay")
	private String isDelay;
    /**
     * 延期截止时间
     */
	@TableField("delay_date")
	private Date delayDate;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;
    /**
     * 创建时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("update_date")
	private Date updateDate;





}
