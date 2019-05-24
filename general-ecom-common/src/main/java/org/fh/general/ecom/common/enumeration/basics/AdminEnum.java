package org.fh.general.ecom.common.enumeration.basics;

public class AdminEnum {


	public enum Shouquan{
				FAIL("1", "业务处理失败"),
		        SUCCESS("0", "业务处理成功");
		        		private String value;
		        		private String name;

		        		public static Shouquan codeOf(String value) {
		        			for (Shouquan c : Shouquan.values()) {
		        				if (c.getValue().equals(value)) {
		        					return c;
		        				}
		        			}
		        			return null;
		        		}

		Shouquan(String value, String name) {
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


	public enum AdminUpdatePwd{
		OLDPWD("2", "原密码不正确"),
		NAME("2", "用户不存在"),
		FAIL("1", "业务处理失败"),
        SUCCESS("0", "业务处理成功");
        		private String value;
        		private String name;

        		public static AdminUpdatePwd codeOf(String value) {
        			for (AdminUpdatePwd c : AdminUpdatePwd.values()) {
        				if (c.getValue().equals(value)) {
        					return c;
        				}
        			}
        			return null;
        		}

		AdminUpdatePwd(String value, String name) {
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
	

	public enum AdminAdd{
		LOGIN_ACCOUNT("5", "登录账号已经存在"),
		EMAIL("4", "邮箱重复"),
		PHONE("3", "手机号重复"),
		NAME("2", "姓名重复"),
        FAIL("1", "业务处理失败"),
        SUCCESS("0", "业务处理成功");
        		private String value;
        		private String name;

        		public static AdminAdd codeOf(String value) {
        			for (AdminAdd c : AdminAdd.values()) {
        				if (c.getValue().equals(value)) {
        					return c;
        				}
        			}
        			return null;
        		}

		AdminAdd(String value, String name) {
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

	

    public enum AdminStatus{
            NORMAL("0","正常"),
            FORBID("1","禁用"),
            DEL("2","删除");

        private String value;
                		private String name;

                		public static AdminStatus codeOf(String value) {
                			for (AdminStatus c : AdminStatus.values()) {
                				if (c.getValue().equals(value)) {
                					return c;
                				}
                			}
                			return null;
                		}

        AdminStatus(String value, String name) {
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

    public enum AdminLogin{

        ADMIN_NULL("7", "管理员信息不存在"),
        FLAG("6", "管理员标识为空"),
        DEL("5", "该管理员已经被删除"),
        FORBID("4", "该管理员已经被禁用"),
        PWD("3", "登录密码错误"),
        NAME("2", "登录用户名不存在"),
        FAIL("1", "业务处理失败"),
        SUCCESS("0", "业务处理成功");
        		private String value;
        		private String name;

        		public static AdminLogin codeOf(String value) {
        			for (AdminLogin c : AdminLogin.values()) {
        				if (c.getValue().equals(value)) {
        					return c;
        				}
        			}
        			return null;
        		}

        AdminLogin(String value, String name) {
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
