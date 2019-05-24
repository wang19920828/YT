package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 黑名单
 * </p>
 *
 * @author wzy
 * @since 2018-09-20
 */
@TableName("tb_blacklist")
@Data
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 手机号
     */
	private String phone;
    /**
     * IP地址
     */
	private String ip;
    /**
     * 手机端唯一标识
     */
	private String uuid;
    /**
     * 次数
     */
	private Integer num;
    /**
     * 状态：0、启用；1、禁用
     */
	private String status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 平台编号：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
	private String branch;
    /**
     * 平台名称：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
	@TableField("branch_name")
	private String branchName;

}
