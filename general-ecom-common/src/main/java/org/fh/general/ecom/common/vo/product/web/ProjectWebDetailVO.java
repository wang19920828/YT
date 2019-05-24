package org.fh.general.ecom.common.vo.product.web;

import lombok.Data;
import org.fh.general.ecom.common.vo.product.project.ProjectFilesVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class ProjectWebDetailVO {

    private Long id;
    //基本信息
    private String projectName;//项目名称
    private String areaAddress;
    private String pcImageUrl;
    private String appImageUrl;
    private String projectSummary;
    private String projectType;
    private String schedule; //预约进度
    private String remainingTime;//剩余时间
    private String projectStatus;//项目状态
    private String contactsTel;

    //登录后要显示的信息
    private BigDecimal totalAmount;//目标金额
    private BigDecimal InvestmentAmount;//起投金额
    private BigDecimal amount;//已经预约金额

    //详情
    private String  pcHeaderImages;
    private String  appHeaderImages;
    private String projectDetail;
    private String programmeDetail;


    private String companyName;
    private String logo;
    private String icon;
    private String projectTypeName;



    private List<ProjectFilesVO> openList;
    private List<ProjectFilesVO> privateList;

}
