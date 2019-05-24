package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 管理员登录日志
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@TableName("tb_admin_login_log")
@Data
public class AdminLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户id
     */
	@TableField("admin_id")
	private Long adminId;
    /**
     * 用户名称
     */
	@TableField("admin_name")
	private String adminName;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 登陆时间
     */
	@TableField("login_time")
	private Date loginTime;
    /**
     * 陆登ip
     */
	@TableField("login_ip")
	private String loginIp;
    /**
     * 退出时间
     */
	@TableField("login_out_time")
	private Date loginOutTime;

}
