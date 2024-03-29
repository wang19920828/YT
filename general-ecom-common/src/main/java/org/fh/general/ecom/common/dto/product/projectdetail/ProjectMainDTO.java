package org.fh.general.ecom.common.dto.product.projectdetail;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class ProjectMainDTO {
    private Long id;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 项目类型id
     */
    private String projectType;
    /**
     * 权益类型
     */
    private String rightsType;
    /**
     * 项目进度
     */
    private String projectSchedule;
    /**
     * 物业权重
     */
    private String propertyWeight;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactsTel;
    /**
     * 建筑面积（平方米）
     */
    private BigDecimal buildArea;
    /**
     * 房间数/门店数
     */
    private Long roomsShopNumber;
    /**
     * 单价(元)
     */
    private BigDecimal unitPrice;
    /**
     * 项目状态
     */
    private String projectStatus;
    /**
     * 项目地址（所在地区）
     */
    private String areaAddress;
    /**
     * 宣传图片pc
     */
    private String pcImageUrl;
    /**
     * 宣传图片app
     */
    private String appImageUrl;
    /**
     * 项目摘要
     */
    private String projectSummary;
    /**
     * 项目来源 0：前台用户   1：后台添加
     */
    private String projectSource;
    /**
     * 如果project_source =0时 咨询项目id
     */
    private Long consultingId;
    private String projectStatusDesc;//状态描述
    private String  companyName;
    private String projectTypeName;
    private String rightsTypeName;
    private String projectScheduleName;
    private String propertyWeightName;
    private String areaAddressName;


}
