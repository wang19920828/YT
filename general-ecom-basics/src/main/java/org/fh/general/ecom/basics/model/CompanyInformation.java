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
 * @since 2018-09-20
 */
@TableName("tb_company_information")
@Data
public class CompanyInformation implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 标题
     */
	private String title;
    /**
     * 子标题
     */
	private String subtitle;
    /**
     * 标题类型
     */
	private String type;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
    /**
     * 是否删除
     */
	private String del;


}
