package org.fh.general.ecom.common.enumeration.order;

public class FormulaEnum {

    //状态：1-待执行分红 2-分红执行成功 3-分红执行失败 4-逾期
    public enum ShareRedStatus {
        RED_STATUS_WAIT("1", "待执行分红"),RED_STATUS_SUCCESS("2", "分红执行成功"),RED_STATUS_FAIL("3", "分红执行失败"),RED_STATUS_OUT("4", "逾期");

        private String name;
        private String value;

        public static FormulaEnum.ShareRedStatus codeOf(String value) {
            for(FormulaEnum.ShareRedStatus a:FormulaEnum.ShareRedStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ShareRedStatus(String value, String name) {
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


   //exp2 审核状态：1-待审核 2-通过 3-驳回
    public enum AuditStatus {
        AUDIT_STATUS_WAIT("1", "待审核"),AUDIT_STATUS_SUCCESS("2", "通过"), AUDIT_STATUS_FAIL("3", "驳回");

        private String name;
        private String value;

        public static FormulaEnum.AuditStatus codeOf(String value) {
            for(FormulaEnum.AuditStatus a:FormulaEnum.AuditStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

       AuditStatus(String value, String name) {
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
