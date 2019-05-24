package org.fh.general.ecom.common.enumeration.basics;

public class ExtraCodeEnum {

    public enum VerifyExtraCode{
        INFO_STATUS_FAULT("3", "附加验证失败"),
        FAIL("1", "业务处理失败"),
        SUCCESS("0", "业务处理成功");
        private String value;
        private String name;

        public static VerifyExtraCode codeOf(String value) {
            for (VerifyExtraCode c : VerifyExtraCode.values()) {
                if (c.getValue().equals(value)) {
                    return c;
                }
            }
            return null;
        }

        VerifyExtraCode(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
}
