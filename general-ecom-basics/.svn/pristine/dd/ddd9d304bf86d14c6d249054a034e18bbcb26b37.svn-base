package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@TableName("tb_email_send_info")
@Data
public class EmailSendInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱发送记录ID
     */
	@TableId(value="email_send_id", type= IdType.AUTO)
	private Long emailSendId;
    /**
     * 收件人姓名
     */
	@TableField("receive_name")
	private String receiveName;
    /**
     * 服务器邮箱地址
     */
	@TableField("ftp_email_adr")
	private String ftpEmailAdr;
    /**
     * 状态 0-正常 1-异常
     */
	private String state;
    /**
     * 类型 1-注册 2-登录密码找回
     */
	private String type;
    /**
     * 收件人邮箱地址
     */
	@TableField("receive_email")
	private String receiveEmail;
    /**
     * 验证机器IP
     */
	@TableField("veri_mach_ip")
	private String veriMachIp;
    /**
     * 发送日期
     */
	@TableField("send_time")
	private Long sendTime;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 操作人id
     */
	@TableField("opt_user_id")
	private Long optUserId;
    /**
     * 操作人姓名
     */
	@TableField("opt_user_name")
	private String optUserName;



}
