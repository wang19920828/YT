package org.fh.general.ecom.common.dto.basics.pay;

import lombok.Data;
import org.fh.general.ecom.common.utils.ToolUtils;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/12 14:36
 * @Description:
 */
@Data
public class WxRechargeInputDTO {

    /**
     * 商品描述（APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值）
     */
    private String body;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 总金额
     */
    private String total_fee;

    /**
     * 终端IP
     */
    private String spbill_create_ip;


    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 随机字符串
     */
    private String nonce_str = ToolUtils.getRandomString(32);

    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 货币类型
     */
    private String fee_type;
    /**
     * 交易起始时间
     */
    private String time_start;
    /**
     * 交易结束时间
     */
    private String time_expire;
    /**
     * 商品标记
     */
    private String goods_tag;
    /**
     * 商品ID
     */
    private String product_id;
    /**
     * 指定支付方式
     */
    private String limit_pay;
    /**
     * 用户标识
     */
    private String openid;

    private String notify_url = "org/notice/wechatRecharge.do";
    /**
     * -----------------------------------------
     */
    private String channelNo;
    private String branch;
    private String accountType;
    private Long userId;
    private String phone;
}
