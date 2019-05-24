package org.fh.general.ecom.common.comm;


import org.fh.general.ecom.common.base.Entity;

public class ExcelHeader implements Entity {
	private static final long serialVersionUID = 1L;
	private String enName;
	private String cnName;
	private Integer  type; // 0: 文本  1：形状  2：金额  3：图片地址 4、条形码 5、二维码
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public ExcelHeader(String enName, String cnName, Integer type) {
		super();
		this.enName = enName;
		this.cnName = cnName;
		this.type = type;
	}
}
