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
@TableName("tb_email_act_info")
@Data
public class EmailActInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱激活记录ID
     */
	@TableId(value="email_act_id", type= IdType.AUTO)
	private Long emailActId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 验证状态 0-未验证 1-已验证
     */
	private String state;
    /**
     * 邮箱地址
     */
	@TableField("email_address")
	private String emailAddress;
    /**
     * 验证机器IP
     */
	@TableField("veri_mach_ip")
	private String veriMachIp;
    /**
     * 验证日期
     */
	@TableField("veri_time")
	private Long veriTime;



}
