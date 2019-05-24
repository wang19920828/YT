package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 项目分红方案表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_red_project_plan")
public class ProjectPlan implements Serializable {

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
     * 利率(eg：值存30，页面显示30%)
     */
	@TableField("rate_white")
	private BigDecimal rateWhite;
    /**
     * 备用字段1
     */
	private String exp1;
    /**
     * 备用字段2
     */
	private String exp2;

}
