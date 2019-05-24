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
 * 交易流水
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@Data
@TableName("tb_deal_flow")
public class DealFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 交易流水号
     */
	@TableField("flow_no")
	private String flowNo;
    /**
     * 订单编号
     */
	@TableField("order_sn")
	private String orderSn;
    /**
     * 支付方式：1-微信公众号支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 交易类型:1-购买、2-充值、3-提现、4-退款)
     */
	@TableField("trans_type")
	private String transType;
    /**
     * 交易状态:0101-交易中，0202-交易成功，0303-交易失败
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
     * 第三方流水
     */
	@TableField("third_flow")
	private String thirdFlow;
    /**
     * 退款时主订单流水
     */
	@TableField("order_flow")
	private String orderFlow;
    /**
     * 摘要
     */
	private String remark;
    /**
     * 添加时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 完成时间
     */
	@TableField("over_time")
	private Date overTime;
    /**
     * 状态(1-正常、2-删除、3-作废)
     */
	private String state;
    /**
     * 第三方返回信息
     */
	@TableField("third_info")
	private String thirdInfo;
    /**
     * 电钱包金额
     */
	@TableField("elec_amt")
	private BigDecimal elecAmt;
    /**
     * 支付方式来源 1.公众号 2.app
     */
	@TableField("pay_type_source")
	private Integer payTypeSource;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
	private Integer channel;
    /**
     * 通知回调地址
     */
	@TableField("notify_url")
	private String notifyUrl;
    /**
     * 通知时间
     */
	@TableField("notify_time")
	private Long notifyTime;
    /**
     * 通知结果：0：失败 1：成功
     */
	@TableField("notify_success")
	private Integer notifySuccess;
    /**
     * 手机号
     */
	private String phone;



}
