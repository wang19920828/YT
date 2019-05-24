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
 * 机构信息表
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
@Data
@TableName("tb_organization_info")
public class OrganizationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 企业名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 企业编号
     */
	@TableField("company_no")
	private String companyNo;
    /**
     * 注册资本
     */
	@TableField("registered_capital")
	private Long registeredCapital;
    /**
     * 法人
     */
	@TableField("legal_person_name")
	private String legalPersonName;
    /**
     * 法人身份证
     */
	@TableField("corporate_identity_card")
	private String corporateIdentityCard;
    /**
     * 统一社会信用代码
     */
	@TableField("social_credit_code")
	private String socialCreditCode;
    /**
     * 有效期
     */
	@TableField("term_of_validit")
	private Date termOfValidit;
    /**
     * 注册区域
     */
	@TableField("registered_address")
	private String registeredAddress;
    /**
     * 详细地址
     */
	@TableField("detail_address")
	private String detailAddress;
    /**
     * 经营范围
     */
	@TableField("scope_of_operation")
	private String scopeOfOperation;
    /**
     * 营业执照图片地址
     */
	@TableField("business_license")
	private String businessLicense;
    /**
     * 开会银行名称
     */
	@TableField("bank_name")
	private String bankName;
    /**
     * 银行账号
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 联系电话
     */
	@TableField("contacts_tel")
	private String contactsTel;
    /**
     * 客户经理
     */
	@TableField("customer_manager")
	private String customerManager;

	@TableField("customer_manager_id")
	private String customerManagerId;
	private String status;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;


	@TableField("create_date")
	private Date createDate;
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
     * 备注
     */
	private String remarks;
    /**
     * 平台编码
     */
	private String branch;
    /**
     * 平台名称
     */
	@TableField("branch_name")
	private String branchName;

	/**
	 * 渠道
	 */
	private String channel;


	private String  logo;
}
