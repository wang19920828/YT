package org.fh.general.ecom.common.enumeration.order;

public class CouponsEnum {


    //优惠券类型:1-项目代金券 2-商品满减券 3-商品抵现券
    public enum Type {
        TYPE_PROJECT("1", "项目代金券"),
        TYPE_MAN_JIAN("2", "商品满减券"),
        TYE_DI_XIAN("3", "商品抵现券");

        private String name;
        private String value;

        public static CouponsEnum.Type codeOf(String value) {
            for(CouponsEnum.Type a:CouponsEnum.Type.values()){
                if(a.getValue().equals(value)){
                    return a;
                }
            }
            return null;
        }

        Type(String value, String name) {
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
