package org.fh.general.ecom.common.dto.product.organization;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/13
 **/
@Data
public class InputOrganizationAddDTO {

    /**
     * 企业名称
     */
    @Check(empty = false,  description = "企业名称")
    private String companyName;

    /**
     * 注册资本
     */
    @Check(empty = false, regexType = RegexType.NUMBER,description = "企业名称")
    private Long registeredCapital;
    /**
     * 法人
     */
    @Check(empty = false, description = "法人")
    private String legalPersonName;
    /**
     * 法人身份证
     */
    @Check(empty = false, description = "法人身份证")
    private String corporateIdentityCard;
    /**
     * 统一社会信用代码
     */
    @Check(empty = false, description = "统一社会信用代码")
    private String socialCreditCode;
    /**
     * 有效期
     */
    @Check(empty = false, description = "有效期")
    private String termOfValidit;
    /**
     * 注册区域
     */
    @Check(empty = false, description = "注册区域")
    private String registeredAddress;
    /**
     * 详细地址
     */
    @Check(empty = false, description = "详细地址")
    private String detailAddress;
    /**
     * 经营范围
     */
    @Check(empty = true, description = "经营范围")
    private String scopeOfOperation;
    /**
     * 营业执照图片地址
     */
    @Check(empty = false, description = "营业执照图片")
    private String businessLicense;
    /**
     * 开会银行名称
     */
    @Check(empty = false, description = "开户银行名称")
    private String bankName;
    /**
     * 银行账号
     */
    @Check(empty = false, description = "银行账号")
    private String bankAccount;
    /**
     * 联系人
     */
    @Check(empty = false, description = "联系人")
    private String contacts;
    /**
     * 联系电话
     */
    @Check(empty = false,regexType = RegexType.PHONENUMBER,description = "联系人")
    private String contactsTel;
    /**
     * 客户经理
     */
    @Check(empty = false,description = "客户经理")
    private String customerManager;

    @Check(empty = false,description = "客户经理id")
    private String customerManagerId;
    /**
     * 创建用户id
     *
     */
    @Check(empty = false,description = "创建用户")
    public String createBy;


    public  String branch;

    public  String branchName;

    public String channel;


    private String  logo;
}
