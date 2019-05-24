package org.fh.general.ecom.product.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 项目披露信息表
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
@Data
@TableName("tb_project_files")
public class ProjectFiles implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;
	/**
	 * 附件名称
	 */
	@TableField("file_name")
	private String fileName;

    /**
     * 附件地址
     */
	@TableField("file_url")
	private String fileUrl;
    /**
     * 备注
     */
	private String remarks;
    /**
     * 公开信息 0   投资人可见 1
     */
	private String open;
    /**
     * 状态 0：正常 1：删除
     */
	private String status;
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
