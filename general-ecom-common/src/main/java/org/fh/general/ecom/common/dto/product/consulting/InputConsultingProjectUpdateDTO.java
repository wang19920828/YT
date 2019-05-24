package org.fh.general.ecom.common.dto.product.consulting;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class InputConsultingProjectUpdateDTO {


    @Check(empty = true,  description = "咨询项目id")
    private Long id;

    /**
     * 项目类型
     */
    @Check(empty = true,  description = "项目类型")
    private String projectType;
    /**
     * 联系人姓名
     */
    @Check(empty = true,  description = "联系人姓名")
    private String contacts;
    /**
     * 联系电话
     */
    @Check(empty = true, regexType = RegexType.PHONENUMBER,description = "联系电话")
    private String contactsTel;
    /**
     * 微信号
     */
    @Check(empty = true,description = "联系电话")
    private String wechat;
    /**
     * 项目名称
     */
    @Check(empty = true,description = "项目名称")
    private String projectName;
    /**
     * 所在区域
     */
    @Check(empty = true,description = "所在区域")
    private String areaAddress;
    /**
     * 详细地址
     */
    @Check(empty = true,description = "详细地址")
    private String detailAddress;
    /**
     * 项目进度
     */
    @Check(empty = true,description = "项目进度")
    private String projectSchedule;
    /**
     * 物业权属
     */
    @Check(empty = true,description = "物业权属")
    private String propertyWeight;
    /**
     * 建筑面积
     */
    @Check(empty = true,description = "建筑面积")
    private BigDecimal buildArea;
    /**
     * 房间数、门店数
     */
    @Check(empty = true,regexType = RegexType.DECIMAL,description = "房间数/门店数")
    private Long roomsShopNumber;
    /**
     * 单价
     */
    @Check(empty = true,regexType = RegexType.DECIMAL,description = "单价")
    private BigDecimal unitPrice;
    /**
     * 众筹金额
     */
    @Check(empty = true,regexType = RegexType.DECIMAL,description = "众筹金额")
    private BigDecimal totalAmount;
    /**
     * 总投金额
     */
    @Check(empty = true,regexType = RegexType.DECIMAL,description = "总投金额")
    private BigDecimal investAmount;


    @Check(empty = true,description ="修改用户")
    private  String  updateBy;

    @Check(empty = true,description = "备注")
    private String remarks;
}
