package org.fh.general.ecom.common.vo.basics.help;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
public class AdverListVO {

    /**
     * 广告ID
     */
    @TableId(value="adver_id", type= IdType.AUTO)
    private Long adverId;
    /**
     * 广告名称
     */
    @TableField("adver_name")
    private String adverName;
    /**
     * 版位Id
     */
    @TableField("place_id")
    private Long placeId;
    /**
     * 开始日期
     */
    @TableField("effective_date")
    private String effectiveDate;
    /**
     * 结束日期
     */
    @TableField("expiry_date")
    private String expiryDate;
    /**
     * 图片地址
     */
    @TableField("img_src")
    private String imgSrc;
    /**
     * 链接地址
     */
    @TableField("link_src")
    private String linkSrc;
    /**
     * 广告状态(1-待发布 2-已发布 3-已失效 4-删除)
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    @TableField("creator_id")
    private Long creatorId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 广告排序
     */
    @TableField("adver_order")
    private Integer adverOrder;
    private String branch;

}
