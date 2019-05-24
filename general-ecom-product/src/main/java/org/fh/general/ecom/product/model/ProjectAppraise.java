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
 * 项目评价信息
 * </p>
 *
 * @author hlp
 * @since 2018-09-21
 */
@Data
@TableName("tb_project_appraise")
public class ProjectAppraise implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 评价用户
     */
	@TableField("appraise_id")
	private String appraiseId;
    /**
     * 评价内容
     */
	@TableField("appraise_content")
	private String appraiseContent;
    /**
     * 评价图片地址，多个之间逗号隔开
     */
	@TableField("appraise_img")
	private String appraiseImg;
    /**
	 * 状态
	 */
	private String status;
	/**
	 * 评价类型 0:评价   1：回复
	 */
	@TableField("appraise_type")
	private String appraiseType;
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


	private  String  phone;

	private String branch;

	@TableField("branch_name")
	private String branchName;

	private String channel ;


}
