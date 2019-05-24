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
 * 分红审核表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_red_audit")
public class RedAudit implements Serializable {

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
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 企业名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 项目唯一标识
     */
	@TableField("project_id")
	private String projectId;
    /**
     * 本期分红日期
     */
	@TableField("current_time")
	private Date currentTime;
	/**
	 * 审核指定分红日期
	 */
	@TableField("zhiding_time")
	private String zhidingTime;
    /**
     * 本次申请金额
     */
	@TableField("current_amount")
	private BigDecimal currentAmount;
    /**
     * 审核状态：1-待审核 2-通过 3-驳回
     */
	private String status;
    /**
     * 申请时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 审核人
     */
	@TableField("user_name")
	private String userName;
    /**
     * 平台编号：1001-云投
     */
	private String branch;
    /**
     * 备用字段1
     */
	private String exp1;
    /**
     * 备用字段2
     */
	private String exp2;

}
