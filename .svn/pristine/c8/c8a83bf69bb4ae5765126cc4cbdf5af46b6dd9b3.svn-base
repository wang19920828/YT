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
 * 管理员角色菜单表
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@TableName("tb_admin_role_fun")
@Data
public class AdminRoleFun implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色功能ID
     */
	@TableId(value="role_fun_id", type= IdType.AUTO)
	private Long roleFunId;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 功能模块编码
     */
	@TableField("fun_module_code")
	private String funModuleCode;
    /**
     * 备注 0 备注正常 1 备注删除
     */
	private String remark;
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

}
