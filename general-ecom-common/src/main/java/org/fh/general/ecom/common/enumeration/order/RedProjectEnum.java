package org.fh.general.ecom.common.enumeration.order;

public class RedProjectEnum {


    //本期逾期状态：1-正常 2-逾期
    public enum OverStatus {
        NORMAL("1", "正常"), OVERDUE("2", "逾期");

        private String name;
        private String value;

        public static RedProjectEnum.OverStatus codeOf(String value) {
            for(RedProjectEnum.OverStatus a:RedProjectEnum.OverStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        OverStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //本期分红状态：1-待申请 2-待审核 3-驳回 4-分红
    public enum ShareStatus {
        WAIT_APPLY("1", "待申请"),WAIT_AUDIT("2", "待审核"),SHARE_NO("3", "驳回"), SHARE_OK("4", "分红");

        private String name;
        private String value;

        public static RedProjectEnum.ShareStatus codeOf(String value) {
            for(RedProjectEnum.ShareStatus a:RedProjectEnum.ShareStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ShareStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    //tb_red_audit_log 操作成功、 2-通过、3-驳回、修改
    public enum RedAuditStatus {
        AUDIT_OK("2", "通过"),
        AUDIT_NO("3", "驳回");

        private String name;
        private String value;

        public static RedProjectEnum.RedAuditStatus codeOf(String value) {
            for(RedProjectEnum.RedAuditStatus a:RedProjectEnum.RedAuditStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        RedAuditStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
