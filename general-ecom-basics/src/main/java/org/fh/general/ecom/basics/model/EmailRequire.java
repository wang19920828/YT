package org.fh.general.ecom.basics.model;

import org.fh.general.ecom.common.valid.Check;

import java.io.Serializable;

public class EmailRequire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2795457444405821526L;
	
	@Check(empty = false, description = "用户名")
	private String name;//
	@Check(empty = false, description = "密码")
	private String pwd;//
	@Check(empty = false, description = "smtp地址")
	private String smtp;//
	@Check(empty = false, description = "备注")
	private String remark;//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
