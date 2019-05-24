package org.fh.general.ecom.common.vo.product.project;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class ProjectListVO {

    private Long id;
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
     * 项目地址（所在地区）
     *
     * */
    private String areaAddress;

    /**
     * 项目来源 0：前台用户   1：后台添加
     */
    private String projectSource;

    /**
     * 咨询项目id
     */
    private Long consultingId;


    private String companyName;


    private String projectTypeName;

    private String  rightsTypeName;


    private String  projectStatus;

}
