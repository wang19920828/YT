package org.fh.general.ecom.common.dto.basics.user.fundJournal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/27 17:03
 * @Description:
 */
@Data
public class FundJournalListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

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
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 支付方式0,电子1:微信
     */
    @TableField("pay_type")
    private String payType;
    /**
     * 交易类型( 1-消费、2-充值、3-提现、4-退款、5-分红)
     */
    @TableField("trans_type")
    private String transType;
    /**
     * 交易状态(0101-支付中，0202-支付成功，0303-支付失败)
     */
    @TableField("trans_stuts")
    private String transStuts;
    /**
     * 第三方流水
     */
    @TableField("channel_journal")
    private String channelJournal;
    /**
     * 开始时间
     */
    private Long timeStart;
    private Long timeEnd;

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
