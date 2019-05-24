package org.fh.general.ecom.common.enumeration.order;

public class GoldTicketEnum {




    //使用状态: 1-未使用 2-已使用 3-已过期
    public enum UseState {
        USE_STATE_WAIT("1", "未使用"),USE_STATE_USED("2", "已使用"), USE_STATE_OUT("3", "已过期");

        private String name;
        private String value;

        public static GoldTicketEnum.UseState codeOf(String value) {
            for(GoldTicketEnum.UseState a:GoldTicketEnum.UseState.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        UseState(String value, String name) {
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
