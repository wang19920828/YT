package org.fh.general.ecom.common.vo.product.projectlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/20
 **/
@Slf4j
@Data
public class ProjectLogDetailVO {

    private Long projectId;
    
    private String projectStatus;
    /**
     * 操作用户
     */

    private String operId;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date operTime;
    /**
     * 操作备注
     */
    private String remarks;
    /**
     * 已经预约金额-预留
     */
    private BigDecimal appointmentAmount;
    /**
     * 预约进度-预留
     */
    private BigDecimal appointmentSchedule;
    /**
     * 预约截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date appointmentEndTime;
    /**
     * 预约状态
     */
    private String appointmentStatus;
    /**
     * 认购截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date subscribeForEndTime;
    /**
     * 认购金额
     */
    private BigDecimal subscribeForAmount;
    /**
     * 认购进度-预留
     */
    private BigDecimal subscribeForSchedule;
    /**
     * 认购状态-预留
     */
    private String subscribeForStatus;
    /**
     * 认购成功比例
     */
    private BigDecimal subscribeForSuccess;
    /**
     * 延期前日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date delayBeforeTime;
    /**
     * 延期后日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date delayAfterTime;
    /**
     * 冷静期
     */
    private String calmnessPeriod;
    /**
     * 退出人数
     */
    private Long quitUserNum;
    /**
     * 退出金额
     */
    private BigDecimal quitTotalAmount;
    /**
     * 实际筹资比例
     */
    private BigDecimal actualSchedule;
    /**
     * 实际筹资金额
     */
    private BigDecimal actualAmount;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 预期筹资总额
     */
    private BigDecimal totalAmount;
    /**
     * 分红第几次
     */
    private Long redNum;
    /**
     * 累计分红
     */
    private BigDecimal redTotalAmount;
    /**
     * 预期本次分红
     */
    private BigDecimal redThisAmount;
    /**
     * 实际分红
     */
    private BigDecimal redActualAmount;
    /**
     * 分红状态
     */
    private String redStatus;
    /**
     * 是否逾期
     */
    private String redBeOverdue;
    /**
     * 分红总次数
     */
    private Long redTotalNum;
    /**
     * 分红预期次数
     */
    private Long redBeOverdueNum;

    private BigDecimal  redYingFuAmount;


    private String  createName;

    private String  projectSource;

    private String redTerm;

}
