package org.fh.general.ecom.common.dto.order.order;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.Date;

@Data
public class OrderAddInputDTO {

    /**
     *门店编号
     */
    @Check(empty = false, description = "门店编号")
    private String shopSn;
    /**
     *下单用户ID
     */
    @Check(empty = false, description = "下单用户ID")
    private Long userId;
    /**
     *买家留言
     */
    private String postscript;
    /**
     *配送方式：0-快递 1-自提
     */
    @Check(empty = false, description = "配送方式：0-快递 1-自提")
    private String isSend;
    /**
     *支付方式：1-微信支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付 6-券兑换 7-平台支付 8-京东到家 9-美团 10-百度 11-人民币  13-理财账户 14- 其他：如活动推广 15-红包',
     */
    private String payType;
    /**
     *支付方式说明
     */
    private String payName;
    /**
     *支付时间
     */
    private Date payTime;
    /**
     *期望送达时间段
     */
    private String hopeTime;
    /**
     *送达时间戳
     */
    private Date hopeTimestamp;
    /**
     *是否是预约订单： 0-否 1- 是
     */
    private String isPre;
    /**
     *订单类型: 0-普通订单 1-积分换购 2-抵货 3-拼团订单 4-健身房扫码开门 5-订单原单(请咖啡) 6-订单副单（请咖啡） 7-预售券兑换商品订单  8-pos单 9-D5自助结账 10-啤酒订单 11-优享会员 12-智奇开锁 13-语音下单 14-预售券原单  ',
     */
    @Check(empty = false, description = "订单类型")
    private String platform;
    /**
     *渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private String channel;
    /**
     *桌位号
     */
    private String deskNo;
    /**
     *平台编号
     */
    private String branch;

}
