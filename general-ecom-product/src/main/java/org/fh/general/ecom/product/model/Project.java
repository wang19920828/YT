package org.fh.general.ecom.product.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 项目主信息
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Data
@TableName("tb_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目编号
     */
	@TableField("project_no")
	private String projectNo;
    /**
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 公司id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 项目类型id
     */
	@TableField("project_type")
	private String projectType;
    /**
     * 权益类型
     */
	@TableField("rights_type")
	private String rightsType;
    /**
     * 项目进度
     */
	@TableField("project_schedule")
	private String projectSchedule;
    /**
     * 物业权重
     */
	@TableField("property_weight")
	private String propertyWeight;
    /**
     * 详细地址
     */
	@TableField("detail_address")
	private String detailAddress;
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
     * 建筑面积（平方米）
     */
	@TableField("build_area")
	private BigDecimal buildArea;
    /**
     * 房间数/门店数
     */
	@TableField("rooms_shop_number")
	private Long roomsShopNumber;
    /**
     * 单价(元)
     */
	@TableField("unit_price")
	private BigDecimal unitPrice;
    /**
     * 项目状态
     */
	@TableField("project_status")
	private String projectStatus;
    /**
     * 项目地址（所在地区）
     */
	@TableField("area_address")
	private String areaAddress;
    /**
     * 宣传图片pc
     */
	@TableField("pc_image_url")
	private String pcImageUrl;
    /**
     * 宣传图片app
     */
	@TableField("app_image_url")
	private String appImageUrl;
    /**
     * 项目摘要
     */
	@TableField("project_summary")
	private String projectSummary;
    /**
     * 项目来源 0：前台用户   1：后台添加
     */
	@TableField("project_source")
	private String projectSource;
    /**
     * 如果project_source =0时 咨询项目id
     */
	@TableField("consulting_id")
	private Long consultingId;
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
	 * 发布时间
	 */
	@TableField("publish_date")
	private Date publishDate;


	/**
	 * 筹资成功时间
	 */
	@TableField("success_date")
	private Date successDate;


    /**
     * 平台编号
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




}
