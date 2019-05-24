package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 邮件
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@TableName("tb_email")
@Data
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱id
     */
	@TableId(value="email_id", type= IdType.AUTO)
	private Long emailId;
    /**
     * 邮件平台
     */
	@TableField("email_channel")
	private String emailChannel;
    /**
     * smtp服务器
     */
	private String smtp;
    /**
     * 端口号
     */
	private String port;
    /**
     * 邮箱地址
     */
	@TableField("email_adr")
	private String emailAdr;
    /**
     * 邮箱密码
     */
	@TableField("eamil_pwd")
	private String eamilPwd;
    /**
     * 发件人昵称或姓名
     */
	@TableField("from_user")
	private String fromUser;
    /**
     * 发件人email地址
     */
	@TableField("email_from")
	private String emailFrom;
    /**
     * 发送email
     */
	@TableField("send_to")
	private String sendTo;
    /**
     * 抄送email
     */
	@TableField("copy_to")
	private String copyTo;


}
