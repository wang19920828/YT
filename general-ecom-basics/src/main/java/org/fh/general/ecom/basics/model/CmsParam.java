package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Data
@TableName("tb_cms_param")
public class CmsParam implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 参数名称
     */
	@TableField("param_name")
	private String paramName;
    /**
     * 参数描述
     */
	@TableField("param_desc")
	private String paramDesc;
    /**
     * 参数key
     */
	@TableField("param_key")
	private String paramKey;
    /**
     * 参数值
     */
	@TableField("param_value")
	private String paramValue;
    /**
     * 扩展字段1
     */
	private String ext1;
    /**
     * 扩展字段2
     */
	private String ext2;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Long updateTime;




}
