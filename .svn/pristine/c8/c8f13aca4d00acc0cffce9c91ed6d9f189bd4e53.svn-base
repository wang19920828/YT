package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 其它参数
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
@TableName("tb_other_parameter")
@Data
public class OtherParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 参数键
     */
	@TableField("param_key")
	private String paramKey;
    /**
     * 参数值
     */
	@TableField("param_value")
	private String paramValue;
    /**
     * 参数名称
     */
	@TableField("param_name")
	private String paramName;
    /**
     * 备注
     */
	private String content;
    /**
     * 状态 0-可用 1-不可用
     */
	private String status;
    /**
     * 参数类型 1-系统参数(不可删除) 2-自定义参数
     */
	@TableField("sys_type")
	private String sysType;
    /**
     * 渠道:1-线下,2-微信,3-Android,4-IOS,5-小程序,6-Web,7-Wap
     */
	private String channel;
    /**
     * 平台编号:1001-D5厨房,1002-美食工场,1003-爱炉火锅,1004-真真小吃
     */
	private String branch;
    /**
     * 平台名称：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
	@TableField("branch_name")
	private String branchName;

}
