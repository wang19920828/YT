package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@TableName("tb_extra_code")
public class ExtraCode implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 6位附加码信息
     */
	@TableField("extra_code")
	private String extraCode;
    /**
     * 请求的浏览器标识
     */
	@TableField("browser_code")
	private String browserCode;
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态 0：未使用   1：使用
     */
	private String status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExtraCode() {
		return extraCode;
	}

	public void setExtraCode(String extraCode) {
		this.extraCode = extraCode;
	}

	public String getBrowserCode() {
		return browserCode;
	}

	public void setBrowserCode(String browserCode) {
		this.browserCode = browserCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
