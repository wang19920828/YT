package org.fh.general.ecom.common.enums;

import lombok.Data;

@Data
public class ComEnum {

    //状态
    public enum IsDelete {
        NORMAL("0", "正常"), DEL("1", "删除");
        private String name;
        private String value;

        public static ComEnum.IsDelete codeOf(String value) {
            for(ComEnum.IsDelete a:ComEnum.IsDelete.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        IsDelete(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //平台
    public enum Branch {
        YUN_TOU("1001", "云投");
        private String name;
        private String value;

        public static ComEnum.Branch codeOf(String value) {
            for(ComEnum.Branch a:ComEnum.Branch.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        Branch(String value, String name) {
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

    public enum Channel {
        WEB("0","WEB"),APP("1","APP");
        private String name;
        private String value;

        public static ComEnum.Channel codeOf(String value) {
            for(ComEnum.Channel a:ComEnum.Channel.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        Channel(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /*
       帮助-文章类型
     */
    public enum NewsOrGuide {
        NEWS("0","新闻"),GUIDE("1","用户指南");
        private String name;
        private String value;

        public static ComEnum.Channel codeOf(String value) {
            for(ComEnum.Channel a:ComEnum.Channel.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        NewsOrGuide(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 是否已发布
     */
    public enum published {
        OREADY("0","已发布"),NOTYET("1","未发布");
        private String name;
        private String value;

        public static ComEnum.Channel codeOf(String value) {
            for(ComEnum.Channel a:ComEnum.Channel.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        published(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }



}
