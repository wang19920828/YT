package org.fh.general.ecom.common.vo.product.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class ProjectWebListVO {

    private Long id;//项目id
    private String projectName;//项目名称
    private String areaAddress;
    private String pcImageUrl;
    private String appImageUrl;
    private String projectSummary;
    private String projectType;

    private String schedule; //预约进度
    private String remainingTime;//剩余时间


    private String projectStatus;

    private String  companyName;

    private  String  logo;
    private String icon;
    private String projectTypeName;
    private String district;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date purchaseStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date purchaseEndTime;

    private Date publishDate;
}
