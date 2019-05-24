package org.fh.general.ecom.common.vo.product.consulting;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class ConsultingProjectDetailVo implements Serializable{

    /**
     * ID
     */
    private Long id;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 联系人姓名
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactsTel;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 所在区域
     */
    private String areaAddress;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 项目进度
     */
    private String projectSchedule;
    /**
     * 物业权属
     */
    private String propertyWeight;
    /**
     * 建筑面积
     */
    private BigDecimal buildArea;
    /**
     * 房间数、门店数
     */
    private Long roomsShopNumber;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 众筹金额
     */
    private BigDecimal totalAmount;
    /**
     * 总投金额
     */
    private BigDecimal investAmount;

    /**
     * 状态0：正常  1：删除   2：已经生成项目
     */
    private String status;
    /**
     * 创建人
     */
    private String createBy;


    private String remarks;

    private String  teamManage;//管理团队



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date createDate;
    private String channel;
    private String projectTypeName;
    private String projectScheduleName;
    private String propertyWeightName;
    private String areaAddressName;
}
