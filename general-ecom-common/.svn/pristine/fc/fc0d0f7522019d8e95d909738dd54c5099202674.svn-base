package org.fh.general.ecom.common.enums;

import lombok.Getter;

/**
 * @Author huliping
 * @DATE 2018/9/20
 **/
@Getter
public class ProjectEnum {

    public enum ProjectIsDelay {
        //0：正常，
        NORMAL("0", "正常"), DELAY("1", "延期");
        private String name;
        private String value;

        public static ProjectEnum.ProjectIsDelay codeOf(String value) {
            for(ProjectEnum.ProjectIsDelay a:ProjectEnum.ProjectIsDelay.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ProjectIsDelay(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    //项目来源0：前台用户   1：后台添加

    public enum ProjectSource {
        PC("0", "前台添加"),APP("1", "后台添加"), MANAGE("2", "后台添加");
        private String name;
        private String value;

        public static ProjectEnum.ProjectSource codeOf(String value) {
            for(ProjectEnum.ProjectSource a:ProjectEnum.ProjectSource.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ProjectSource(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }


    public enum ProjectFileOpen {
        OPEN("0", "公开信息"), CLOSED("1", "投资人可见");
        private String name;
        private String value;

        public static ProjectEnum.ProjectFileOpen codeOf(String value) {
            for(ProjectEnum.ProjectFileOpen a:ProjectEnum.ProjectFileOpen.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ProjectFileOpen(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }


    public enum ProjectStatus {
        /**
         * 项目状态\r\n
         * 00-申请中---web页面新增咨询项目时，为该状态,
         * 01-完善中--只有该状态的项目才允许修改
         * 02- 发布待审核---后台新增项目点击发布为该状态，该状态不允许修改信息
         * 03-审核通过（发布到前台展示）
         * 04-审核驳回
         * 05-预约中（预约时间开始后修改为该状态）\
         * 06- 预约认购中（预约结束后的48小时内是该状态）
         * 07-公开认购中（预约时间结束后修改为该状态）\r\n
         * 08-冷静期（认购期结束修改为该状态24小时）\r\n
         * 09-延期
         * 10-认购失败
         * 11-项目发布撤销
         * 12-认购成功
         * 13-分红中
         * 14-项目完成（项目结束）
         */
        CREATE("00","申请中"),
        APPLYING("01", "完善中"),
        AUDITING("02","待审核"),
        AUDIT_PASS("03", "审核通过"),
        AUDIT_REJECT("04", "审核驳回"),
        APPOINTMENT("05", "预约中"),
        APPOINTMENT_SUBSCRIBE("06", "预约认购中"),
        SUBSCRIBE("07", "公开认购中"),
        CALMNESS_PERIOD("08", "冷静期"),
        DELAY("09", "延期"),
        FAIL("10", "认购失败"),
        CANCLE("11", "撤销"),
        SUCCESS("12", "项目认购成功"),
        IN_RED("13", "分红中"),
        FINISH("14", "项目完成");

        private String name;
        private String value;

        public static ProjectEnum.ProjectStatus codeOf(String value) {
            for(ProjectEnum.ProjectStatus a:ProjectEnum.ProjectStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ProjectStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() { return name;}

    }


    public enum ProjectAppraiseType {
        APPRAISE_TYPE("0", "评价"), REPLAY_APPRISE("1", "回复");
        private String name;
        private String value;

        public static ProjectEnum.ProjectAppraiseType codeOf(String value) {
            for(ProjectEnum.ProjectAppraiseType a:ProjectEnum.ProjectAppraiseType.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        ProjectAppraiseType(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

//'退款状态（认购失败，购买这个项目的订单批量退款）；1-已支付 2-申请退款 3-已退款',
    public enum RefundStatus {
        PAY("1", "已支付"), APPLAY("2", "申请退款"),REFUND_SUCCESS("3","已退款");
        private String name;
        private String value;

        public static ProjectEnum.RefundStatus codeOf(String value) {
            for(ProjectEnum.RefundStatus a:ProjectEnum.RefundStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        RefundStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }



    public enum AppointmentStatus {
        START("-1","认购中"), FAIL("0", "预约失败"), SUCCESS("1", "预约成功");
        private String name;
        private String value;

        public static ProjectEnum.AppointmentStatus codeOf(String value) {
            for(ProjectEnum.AppointmentStatus a:ProjectEnum.AppointmentStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        AppointmentStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }


    //10-认购失败 12-认购成功
    public enum SubscribeForStatus {
        START("-1","认购中"),FAIL("10", "认购失败"), SUCCESS("12", "认购成功");
        private String name;
        private String value;

        public static ProjectEnum.SubscribeForStatus codeOf(String value) {
            for(ProjectEnum.SubscribeForStatus a:ProjectEnum.SubscribeForStatus.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        SubscribeForStatus(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }



}
