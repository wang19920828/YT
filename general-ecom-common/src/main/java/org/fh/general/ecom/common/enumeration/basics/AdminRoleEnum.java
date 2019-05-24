package org.fh.general.ecom.common.enumeration.basics;

public class AdminRoleEnum {

    public enum AdminRoleDisable {
        DISABLE("1", "禁用"), EBABLE("0", "启用");
        private String value;
        private String name;

        public static AdminRoleDisable codeOf(String value) {
            for (AdminRoleDisable c : AdminRoleDisable.values()) {
                if (c.getValue().equals(value)) {
                    return c;
                }
            }
            return null;
        }

        AdminRoleDisable(String value, String name) {
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

    public enum AdminRoleStatus {
        DISABLE("1", "禁用"), EBABLE("0", "启用");
        private String value;
        private String name;

        public static AdminRoleStatus codeOf(String value) {
            for (AdminRoleStatus c : AdminRoleStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c;
                }
            }
            return null;
        }

        AdminRoleStatus(String value, String name) {
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

    public enum AdminRoleAdd {
        NAME("2", "角色已经存在"), FAIL("1", "业务处理失败"), SUCCESS("0", "业务处理成功");
        private String value;
        private String name;

        public static AdminRoleAdd codeOf(String value) {
            for (AdminRoleAdd c : AdminRoleAdd.values()) {
                if (c.getValue().equals(value)) {
                    return c;
                }
            }
            return null;
        }

        AdminRoleAdd(String value, String name) {
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
