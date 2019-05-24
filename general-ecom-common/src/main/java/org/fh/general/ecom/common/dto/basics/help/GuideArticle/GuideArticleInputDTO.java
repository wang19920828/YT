package org.fh.general.ecom.common.dto.basics.help.GuideArticle;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class GuideArticleInputDTO {
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
    private String branch;
    @TableField("branch_name")
    private String branchName;

}
