package org.fh.general.ecom.product.model;

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
 * @author hlp
 * @since 2018-09-13
 */
@Data
@TableName("tb_organization_log")
public class OrganizationLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 机构id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("update_date")
	private Date updateDate;
    /**
     * 修改备注
     */
	private String remarks;
    /**
     * 修改用户名
     */
	@TableField("update_name")
	private String updateName;




}
