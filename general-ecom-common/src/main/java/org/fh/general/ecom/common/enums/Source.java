package org.fh.general.ecom.common.enums;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/29 15:13
 * @Description:
 */
public enum Source {
    /**
     * 产品
     */
    PRODUCT(1, "产品"),
    /**
     * 图书
     */
    BOOK(2, "图书"),

    /**
     * 红包活动
     */
    REB_BAG(3, "红包活动"),
    /**
     * 扫码开门
     */
    OPEN_DOOR(4, "扫码开门"),
    /**
     * 自助机支付
     */
    AUTO_ONLINE_PAY(5, "自助机线上支付"),
    /**
     * 扫码支付
     */
    SCAN_PAY(6, "线下扫码"),
    /**
     * 自助机线下支付
     */
    AUTO_OFFLINE_PAY(7, "自助机线下支付");


    private int value;
    private String desc;

    Source(int val, String desc) {
        this.value = val;
        this.desc = desc;
    }

    public static Source valueOf(Integer value) {    //    手写的从int到enum的转换函数

        if (value == null) {
            return null;
        }
        for (Source status : Source.values()) {

            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    public static Source byValueStr(String value) {    //    手写的从str到enum的转换函数

        return valueOf(Integer.valueOf(value));
    }

    public int value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }
}
