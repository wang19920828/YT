package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分红试算/日历详情表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_red_project_formula")
public class ProjectFormula implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 分红项目ID
     */
	@TableField("red_project_id")
	private Long redProjectId;
	/**
	 * 产品项目ID
	 */
	@TableField("product_id")
	private Long productId;
    /**
     * 投资人ID
     */
	@TableField("invest_id")
	private Long investId;
    /**
     * 投资人姓名
     */
	@TableField("invest_name")
	private String investName;

	/**
	 * 方案ID
	 */
	@TableField("plan_id")
	private Long planId;
    /**
     * 方案名称
     */
	@TableField("plan_name")
	private String planName;
    /**
     * 投资金额
     */
	@TableField("amount_invest")
	private BigDecimal amountInvest;
    /**
     * 累计分红金额
     */
	@TableField("amount_leiji")
	private BigDecimal amountLeiji;
    /**
     * 本期预期年化收益率
     */
	@TableField("benqi_pre_year_rate")
	private BigDecimal benqiPreYearRate;
    /**
     * 本期实际年化收益率
     */
	@TableField("benqi_real_year_rate")
	private BigDecimal benqiRealYearRate;
    /**
     * 本期预期分红金额/元
     */
	@TableField("benqi_pre_red_amount")
	private BigDecimal benqiPreRedAmount;
    /**
     * 本期实际分红金额/元
     */
	@TableField("benqi_real_red_amount")
	private BigDecimal benqiRealRedAmount;
    /**
     * 备用字段1
     */
	private String exp1;
    /**
     * 备用字段2
     */
	private String exp2;
	/**
	 * 实际分红日期
	 */
	@TableField("real_share_time")
	private Date realShareTime;

	/**
	 * 预计分红日期
	 */
	@TableField("pre_share_time")
	private Date preShareTime;

	@TableField("order_sn")
	private String orderSn;
	/**
	 * 分红失败备注
	 */
	private String remark;
	/**
	 * 电子账户
	 */
	@TableField("account_no")
	private String accountNo;
	/**
	 * 创建时间
	 */
	@TableField("add_time")
	private Date addTime;

	/**
	 * 第几次分红
	 */
	private Long numbers;
	/**
	 * 总次数
	 */
	@TableField("all_numbers")
	private Long allNumbers;
}
