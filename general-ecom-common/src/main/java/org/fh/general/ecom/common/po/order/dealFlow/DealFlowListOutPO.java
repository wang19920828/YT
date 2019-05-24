package org.fh.general.ecom.common.po.order.dealFlow;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DealFlowListOutPO {

    /**
     * 流水ID
     */
    private Long id;
    /**
     * 交易流水号
     */
    private String flowNo;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 支付方式：1-微信公众号支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付
     */
    private String payType;
    /**
     * 交易类型:1-购买、2-充值、3-提现、4-退款)
     */
    private String transType;
    /**
     * 交易状态:0101-交易中，0202-交易成功，0303-交易失败
     */
    private String transStuts;
    /**
     * 支付金额
     */
    private BigDecimal transAmt;
    /**
     * 优惠券优惠金额
     */
    private BigDecimal yhqAmt;
    /**
     * 积分抵现金额
     */
    private BigDecimal scoreAmt;
    /**
     * 第三方流水
     */
    private String thirdFlow;
    /**
     * 退款时主订单流水
     */
    private String orderFlow;
    /**
     * 摘要
     */
    private String remark;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 完成时间
     */
    private Date overTime;
    /**
     * 状态(1-正常、2-删除、3-作废)
     */
    private String state;
    /**
     * 第三方返回信息
     */
    private String thirdInfo;
    /**
     * 电钱包金额
     */
    private BigDecimal elecAmt;
    /**
     * 支付方式来源 1.公众号 2.app
     */
    private Integer payTypeSource;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private Integer channel;
    /**
     * 通知回调地址
     */
    private String notifyUrl;
    /**
     * 通知时间
     */
    private Long notifyTime;
    /**
     * 通知结果：0：失败 1：成功
     */
    private Integer notifySuccess;
    /**
     * 手机号
     */
    private String phone;



}
