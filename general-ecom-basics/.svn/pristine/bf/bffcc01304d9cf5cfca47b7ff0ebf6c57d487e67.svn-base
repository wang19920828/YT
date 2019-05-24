package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 功能菜单
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@TableName("tb_admin_function")
@Data
public class AdminFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
	@TableId(value="fun_module_id", type= IdType.AUTO)
	private Long funModuleId;
    /**
     * 编码
     */
	@TableField("sort_code")
	private String sortCode;
    /**
     * 功能名称
     */
	private String name;
    /**
     * 菜单路径
     */
	@TableField("func_url")
	private String funcUrl;
    /**
     * 父功能id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 功能标识
     */
	private String code;
    /**
     * 是否禁用 0-否 1-是
     */
	@TableField("is_disabled")
	private String isDisabled;
    /**
     * 创建人id
     */
	@TableField("creator_id")
	private Long creatorId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 排序
     */
	private Long sort;
    /**
     * 是否删除 0 否 1 是
     */
	@TableField("is_del")
	private String isDel;
    /**
     * 渠道 0 全时便利
     */
	private String channel;

	private String selected;//是否选中：0否1是
	private List<AdminFunction> childFuncs;

}
