package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 账户信息日志表
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@TableName("tb_user_accinfo")
@Data
public class UserAccinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 账户
     */
	private String account;
    /**
     * 消费金额
     */
	private BigDecimal amount;
    /**
     * 消费积分
     */
	private Long credits;
	/**
	 * 平台
	 */
	private String branch;
    /**
     * 渠道:1-线下,2-微信,3-Android,4-IOS,5-小程序,6-Web,7-Wap
     */
	private String channel;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


}
