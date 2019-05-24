package org.fh.general.ecom.common.dto.basics.user.fundJournal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/19 10:42
 * @Description:
 */
@Data
public class FundJournalListOutPO {

    /**
     * 流水ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 交易流水号
     */
    @TableField("journal_no")
    private String journalNo;
    /**
     * 订单ID
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 支付方式1:微信
     */
    @TableField("pay_type")
    private String payType;
    /**
     * 交易类型( 1-购买、2-充值、3-提现、4-退款、5-分红)
     */
    @TableField("trans_type")
    private String transType;
    /**
     * 交易状态(0101-支付中，0202-支付成功，0303-支付失败)
     */
    @TableField("trans_stuts")
    private String transStuts;
    /**
     * 支付金额
     */
    @TableField("trans_amt")
    private BigDecimal transAmt;
    /**
     * 优惠券优惠金额
     */
    @TableField("yhq_amt")
    private BigDecimal yhqAmt;
    /**
     * 积分抵现金额
     */
    @TableField("score_amt")
    private BigDecimal scoreAmt;
    /**
     * 交易渠道:1001-丽郎酒店
     */
    @TableField("channel_no")
    private String channelNo;
    /**
     * 第三方流水
     */
    @TableField("channel_journal")
    private String channelJournal;
    /**
     * 退款时主订单流水
     */
    @TableField("order_journal")
    private String orderJournal;
    /**
     * 摘要
     */
    private String remark;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Long addTime;
    /**
     * 完成时间
     */
    @TableField("over_time")
    private Long overTime;
    /**
     * 状态(1-正常、2-删除、3-作废)
     */
    private String state;
    /**
     * 已退款金额
     */
    @TableField("refund_amt")
    private BigDecimal refundAmt;
    /**
     * 平台
     */
    private String branch;
    /**
     * 账户
     */
    @TableField("account_no")
    private String accountNo;
    /**
     * 账户余额
     */
    @TableField("cash_account")
    private BigDecimal cashAccount;
    /**
     * 用户的id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 订单类型: 0-预约 1-预约认购 2-公开认购
     */
    @TableField("order_type")
    private String orderType;


}
