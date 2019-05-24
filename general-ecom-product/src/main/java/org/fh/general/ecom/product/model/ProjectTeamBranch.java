package org.fh.general.ecom.product.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 项目平台团队
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */

@Data
@TableName("tb_project_team_branch")
public class ProjectTeamBranch implements Serializable {

    private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 用户id
     */
	@TableField("admin_id")
	private String adminId;
    /**
     * 用户姓名
     */
	private String name;
    /**
     * 角色id
     */
	@TableField("role_id")
	private String roleId;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;


	@TableField("create_date")
	private Date createDate;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("update_date")
	private Date updateDate;





}
