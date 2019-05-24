package org.fh.general.ecom.common.enumeration.basics;

public class RedisEnum {

    public enum RedisKey{
    				USERLOGIN("user_login_invest","用户登录缓存key"),
    		        QUANXIAN("admin_role_invest","管理员权限缓存"),
    		        ADMINLOGIN("login_admin_invest","管理员登录缓存内的  key ");

    	        private String value;
    	                		private String name;

    	                		public static RedisKey codeOf(String value) {
    	                			for (RedisKey c : RedisKey.values()) {
    	                				if (c.getValue().equals(value)) {
    	                					return c;
    	                				}
    	                			}
    	                			return null;
    	                		}

    		RedisKey(String value, String name) {
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
