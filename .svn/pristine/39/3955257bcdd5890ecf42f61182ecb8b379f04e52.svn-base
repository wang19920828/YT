package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@TableName("tb_admin_role")
@Data
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色类型 0-系统管理员 1-门店管理员 2-供应商管理员
     */
	@TableField("role_type")
	private String roleType;
    /**
     * 备注
     */
	private String remark;
    /**
     * 是否禁用 0-否 1-是
     */
	@TableField("is_disabled")
	private String isDisabled;
    /**
     * 创建人id
     */
	@TableField("creator_id")
	private Long creatorId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 渠道 0 全时便利
     */
	private String channel;
    /**
     * 是否删除 0 否 1 是
     */
	@TableField("is_del")
	private String isDel;
    /**
     * 平台标识
     */
	private String branch;
    /**
     * 平台名称
     */
	@TableField("branch_name")
	private String branchName;

}
