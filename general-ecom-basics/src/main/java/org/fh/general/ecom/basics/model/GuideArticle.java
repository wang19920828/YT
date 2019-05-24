package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
@TableName("tb_guide_article")
@Data
public class GuideArticle implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("guide_id")
	private Long guideId;
    /**
     * 类型，0：1
     */
	private String type;
	private String information;
	@TableField("create_date")
	private String createDate;
	@TableField("update_date")
	private String updateDate;
	private String del;
	private String published;
	private String title;
	private String source;
	@TableField("publish_date")
	private String publishDate;
	private String img;
	private String summary;
	private String imgmob;
	@TableField("branch_name")
	private String branchName;
	private String branch;
}
