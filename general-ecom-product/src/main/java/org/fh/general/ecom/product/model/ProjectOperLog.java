package org.fh.general.ecom.product.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlp
 * @since 2018-09-20
 */
@Data
@TableName("tb_project_oper_log")
public class ProjectOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;

	@TableField("project_status")
	private String projectStatus;
    /**
     * 操作用户
     */
	@TableField("oper_id")
	private String operId;
    /**
     * 操作时间
     */
	@TableField("oper_time")
	private Date operTime;
    /**
     * 操作备注
     */
	private String remarks;
    /**
     * 已经预约金额-预留
     */
	@TableField("appointment_amount")
	private BigDecimal appointmentAmount;
    /**
     * 预约进度-预留
     */
	@TableField("appointment_schedule")
	private BigDecimal appointmentSchedule;
    /**
     * 预约截止时间
     */
	@TableField("appointment_end_time")
	private Date appointmentEndTime;
    /**
     * 预约状态
     */
	@TableField("appointment_status")
	private String appointmentStatus;
    /**
     * 认购截止时间
     */
	@TableField("subscribe_for_end_time")
	private Date subscribeForEndTime;
    /**
     * 认购金额
     */
	@TableField("subscribe_for_amount")
	private BigDecimal subscribeForAmount;
    /**
     * 认购进度-预留
     */
	@TableField("subscribe_for_schedule")
	private BigDecimal subscribeForSchedule;
    /**
     * 认购状态-预留
     */
	@TableField("subscribe_for_status")
	private String subscribeForStatus;
    /**
     * 认购成功比例
     */
	@TableField("subscribe_for_success")
	private BigDecimal subscribeForSuccess;
    /**
     * 延期前日期
     */
	@TableField("delay_before_time")
	private Date delayBeforeTime;
    /**
     * 延期后日期
     */
	@TableField("delay_after_time")
	private Date delayAfterTime;
    /**
     * 冷静期
     */
	@TableField("calmness_period")
	private String calmnessPeriod;
    /**
     * 退出人数
     */
	@TableField("quit_user_num")
	private Long quitUserNum;
    /**
     * 退出金额
     */
	@TableField("quit_total_amount")
	private BigDecimal quitTotalAmount;
    /**
     * 实际筹资比例
     */
	@TableField("actual_schedule")
	private BigDecimal actualSchedule;
    /**
     * 实际筹资金额
     */
	@TableField("actual_amount")
	private BigDecimal actualAmount;
    /**
     * 退款状态
     */
	@TableField("refund_status")
	private String refundStatus;
    /**
     * 预期筹资总额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
    /**
     * 分红第几次
     */
	@TableField("red_num")
	private Long redNum;
    /**
     * 累计分红
     */
	@TableField("red_total_amount")
	private BigDecimal redTotalAmount;
    /**
     * 预期本次分红
     */
	@TableField("red_this_amount")
	private BigDecimal redThisAmount;
    /**
     * 实际分红
     */
	@TableField("red_actual_amount")
	private BigDecimal redActualAmount;
    /**
     * 分红状态
     */
	@TableField("red_status")
	private String redStatus;
    /**
     * 是否逾期
     */
	@TableField("red_be_overdue")
	private String redBeOverdue;
    /**
     * 分红总次数
     */
	@TableField("red_total_num")
	private Long redTotalNum;
    /**
     * 分红逾期次数
     */
	@TableField("red_be_overdue_num")
	private Long redBeOverdueNum;

	@TableField("red_yingfu_amount")
	private BigDecimal  redYingFuAmou;

	@TableField("project_source")
	private String  projectSource;

	@TableField("red_term")
	private String redTerm;


}
