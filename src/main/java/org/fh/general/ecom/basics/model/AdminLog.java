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
 * 管理员操作日志
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@TableName("tb_admin_log")
@Data
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员操作ID
     */
	@TableId(value="admin_log_id", type= IdType.AUTO)
	private Long adminLogId;
    /**
     * 管理员ID
     */
	@TableField("admin_id")
	private Long adminId;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 操作模块
     */
	@TableField("oper_module")
	private String operModule;
    /**
     * 操作结果 0-失败 1-成功
     */
	@TableField("oper_result")
	private String operResult;
    /**
     * 说明
     */
	private String remark;
    /**
     * 操作时间
     */
	@TableField("oper_time")
	private Date operTime;

}
