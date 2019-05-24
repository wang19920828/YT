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

}
