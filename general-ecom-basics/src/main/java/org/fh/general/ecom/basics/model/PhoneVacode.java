package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wzy
 * @since 2018-09-20
 */
@TableName("tb_phone_vacode")
@Data
public class PhoneVacode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 验证码
     */
	@TableField("phone_code")
	private String phoneCode;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 0:未用   1:已经使用
     */
	private String status;
    /**
     * 业务功能码
     */
	@TableField("business_code")
	private String businessCode;
    /**
     * 发送的短信内容
     */
	@TableField("sms_content")
	private String smsContent;
	private String branch;


}
