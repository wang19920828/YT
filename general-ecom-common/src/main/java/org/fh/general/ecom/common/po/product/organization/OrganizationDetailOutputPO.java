package org.fh.general.ecom.common.po.product.organization;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * service 中查询出的 表实体 转换的对象 放到OutPutDTO中返回到control
 * @Author huliping
 * @DATE 2018/9/13
 **/
@Data
public class OrganizationDetailOutputPO {


    private Long id;
    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业编号
     */
    private String companyNo;

    /**
     * 注册资本
     */
    private Long registeredCapital;
    /**
     * 法人
     */
    private String legalPersonName;
    /**
     * 法人身份证
     */
    private String corporateIdentityCard;
    /**
     * 统一社会信用代码
     */
    private String socialCreditCode;
    /**
     * 有效期
     */
    private Date termOfValidit;
    /**
     * 注册区域
     */
    private String registeredAddress;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 经营范围
     */
    private String scopeOfOperation;
    /**
     * 营业执照图片地址
     */
    private String businessLicense;
    /**
     * 开会银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactsTel;
    /**
     * 客户经理
     */
    private String customerManager;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
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
    private String branchName;


    List<OrganizationLogOutputPO> list;
}
