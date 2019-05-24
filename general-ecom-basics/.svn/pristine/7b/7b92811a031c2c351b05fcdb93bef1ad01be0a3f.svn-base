package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
@TableName("tb_adver_place")
@Data
public class AdverPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告版位ID
     */
	@TableId(value="place_id", type= IdType.AUTO)
	private Long placeId;
    /**
     * 版位名称
     */
	@TableField("place_name")
	private String placeName;
    /**
     * 版位描述
     */
	@TableField("place_desc")
	private String placeDesc;
    /**
     * 版位类型(1-图片 2-Flash 3-文字 4-代码
     */
	@TableField("place_type")
	private Integer placeType;
    /**
     * 版位尺寸
     */
	@TableField("place_width_height")
	private Long placeWidthHeight;
    /**
     * 创建人ID
     */
	@TableField("creator_id")
	private Long creatorId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 版位标识
     */
	@TableField("place_sign")
	private String placeSign;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


}
