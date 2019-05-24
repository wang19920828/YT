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
 * 项目管理团队信息
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Data
@TableName("tb_project_team_manage")
public class ProjectTeamManage implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO )
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 姓名
     */
	private String name;
    /**
     * 年龄
     */
	private Long age;
    /**
     * 职位
     */
	private String position;
    /**
     * 从业经验
     */
	@TableField("practice_experience")
	private String practiceExperience;
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
