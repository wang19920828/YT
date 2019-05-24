package org.fh.general.ecom.common.dto.basics.user.fundJournal;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/27 16:40
 * @Description:
 */
@Data
public class FundJournalInsertInputDTO {


    /**
     * 资金交易流水号
     */
    @TableField("journal_no")
    private String journalNo;
    /**
     * 电子钱包余额
     */
    @TableField("dzqb_amt")
    private BigDecimal dzqbAmt;
    /**
     * 电子账户
     */
    @TableField("ele_acct")
    private String eleAcct;
    /**
     * 收支类型(1-充值，2-消费，3-提现,4:赠送,5:分红)
     */
    @TableField("sz_type")
    private String szType;
    /**
     * 交易金额
     */
    @TableField("trans_amt")
    private String transAmt;
    /**
     * 备注
     */
    private String remark;


    @TableField("admin_id")
    private Long adminId;
    @TableField("admin_name")
    private String adminName;

    /**
     * 支付方式：1-微信支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付 6-券兑换 7-平台支付 8-京东到家 9-美团 10-百度 11-人民币  13-理财账户 14- 其他：如活动推广 15-红包
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 平台
     */
    private String branch;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 平台名称
     */
    private String branchName;



}
