package org.fh.general.ecom.order.model;

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
 * 分红日历表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_calendar")
public class Calendar implements Serializable {

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

	@TableField("project_id")
	private Long projectId;
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
     * 当期分红年化利率
     */
	@TableField("current_red_year_rate")
	private BigDecimal currentRedYearRate;
    /**
     * 分红金额/元
     */
	@TableField("share_amount")
	private BigDecimal shareAmount;
    /**
     * 分红时间
     */
	@TableField("share_time")
	private Date shareTime;
	/**
	 * 分红日期
	 */
	@TableField("share_day")
	private String shareDay;
    /**
     * 备用字段1
     */
	private String exp1;
    /**
     * 备用字段2
     */
	private String exp2;

}
