package org.fh.general.ecom.common.enums;

import org.fh.general.ecom.common.utils.StringUtils;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/29 15:09
 * @Description:
 */
/**
 * 支付方式：1-微信支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付 6-券兑换 7-平台支付 8-京东到家 9-美团 10-百度 11-人民币  13-理财账户 14- 其他：如活动推广 15-红包
 */
public enum PayType {
    /**
     * 微信支付
     */
    WX(1, "微信"),
    /**
     * QQ钱包支付
     */
    QQ(2, "QQ钱包"),
    /**
     * 支付宝支付
     */
    ALI(3, "支付宝"),
    /**
     * 电子账户
     */
    ELECTRONICS(4, "电子账户"),
    /**
     * 电子卡
     */
    E_CARD(5, "电子卡"),
    /**
     * 券兑换
     */
    COUPON(6, "劵兑换"),

    /**
     * 平台支付
     */
    PLATFORM(7, "平台支付"),

    /**
     * 京东
     */
    JD(8, "京东"),
    /**
     * 美团
     */
    MEITUAN(9, "美团"),
    /**
     * 百度
     */
    BAIDU(10, "百度"),
    /**
     * 现金
     */
    CASH(11, "现金"),

    /**
     * 理财账户
     */
    FINANCING(13, "理财账户"),
    /**
     * 其他：如活动推广
     */
    OTHER(14, "其他"),
    /**
     * 红包
     */
    REDBAG(15, "红包"),
    /**
     * 自助微信
     */
    WXSELF(16, "自助微信"),
    /**
     * 自助支付宝
     */
    ALISELF(17, "自助支付宝");

    private int value;
    private String desc;

    PayType(int val, String desc) {
        this.value = val;
        this.desc = desc;
    }

    public static PayType valueOf(Integer value) {    //    手写的从int到enum的转换函数
        if (value == null) {
            return null;
        }
        for (PayType accountType : PayType.values()) {

            if (accountType.value == value) {
                return accountType;
            }
        }
        return null;
    }

    public static PayType byValueStr(String value) {    //    手写的从str到enum的转换函数
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return valueOf(Integer.valueOf(value));
    }

    public int value() {
        return this.value;
    }
    public String desc() {
        return this.desc;
    }
}
