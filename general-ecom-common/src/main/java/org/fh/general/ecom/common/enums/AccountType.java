package org.fh.general.ecom.common.enums;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/29 15:11
 * @Description:
 */

import org.fh.general.ecom.common.utils.StringUtils;

/**
     * 账户类型
     */
    public enum AccountType {
        /**
         * 电子账户
         */
        ELECTRONICS(1, "电子钱包"),

        /**
         * 理财账户
         */
        FINANCING(3, "理财金"),
        /**
         * 现金
         */
        CASH(4, "现金"),
        /**
         * 现金
         */
        OTHER(5, "其他"),
        /**
         * 现金
         */
        REDBAG(6, "赠送账户");

        private int value;
        private String desc;

        AccountType(int val, String desc) {
            this.value = val;
            this.desc = desc;
        }

        public static AccountType valueOf(Integer value) {    //    手写的从int到enum的转换函数
            if (value == null) {
                return null;
            }
            for (AccountType accountType : AccountType.values()) {

                if (accountType.value == value) {
                    return accountType;
                }
            }
            return null;
        }

        public static AccountType byValueStr(String value) {    //    手写的从str到enum的转换函数
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
