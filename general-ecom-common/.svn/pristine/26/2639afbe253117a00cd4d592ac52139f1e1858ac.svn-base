package org.fh.general.ecom.common.dto.basics.user.fundJournal;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/19 11:30
 * @Description:
 */
@Data
public class FundJournalExcelOutPO {
    /**
     * 交易流水号
     */
    @TableField("journal_no")
    private String journalNo;
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
     * 支付金额
     */
    @TableField("trans_amt")
    private BigDecimal transAmt;

    /**
     * 完成时间
     */
    private String overTime;

}
