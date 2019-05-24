package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@TableName("tb_admin")
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理用户ID
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 管理用户名
     */
    private String name;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 管理用户密码
     */
    @TableField("login_pwd")
    private String loginPwd;
    /**
     * 二级密码
     */
    @TableField("login_second_pwd")
    private String loginSecondPwd;
    /**
     * 手机号
     */
    @TableField("admin_phone")
    private String adminPhone;
    /**
     * 邮箱
     */
    @TableField("admin_email")
    private String adminEmail;
    /**
     * 最后登陆IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 最后一次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户状态：0正常 1：禁用  2 逻辑删除
     */
    private String status;
    /**
     * 创建人ID
     */
    @TableField("create_user_id")
    private Long createUserId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人ID
     */
    @TableField("modify_user_id")
    private Long modifyUserId;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;
    /**
     * 是否锁屏 0:否   1:是   默认0
     */
    @TableField("is_lock_screen")
    private String isLockScreen;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 管理用户类型 0--系统管理员  1--门店管理员 2--第三方供应商管理员 3--门店员工 4-－供应商员工
     */
    @TableField("admin_type")
    private String adminType;
    /**
     * 平台标识
     */
    private String branch;
    /**
     * 平台名称
     */
    @TableField("branch_name")
    private String branchName;

    private AdminRole adminRole;
    private List<AdminFunction> allFunction;
    private String allFunctionstr;
    private AdminLoginLog adminLoginLog;



}
