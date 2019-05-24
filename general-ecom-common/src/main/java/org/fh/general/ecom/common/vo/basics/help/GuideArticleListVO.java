package org.fh.general.ecom.common.vo.basics.help;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class GuideArticleListVO {

    private Long id;
    @TableField("guide_id")
    private Long guideId;
    private String type;
    private String title;
    @TableField("publish_date")
    private String publishDate;
    private String img;
    private String imgmob;
    private String summary;
    @TableField("branch_name")
    private String branchName;
    private String branch;
    private String month;
}
