package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class InputProjectUpdateMainDTO {
    @Check(empty = true,description = "项目名称")
    private String projectName;

    @Check(empty = true,description ="项目公司")
    private String companyId;

    @Check(empty = true,description ="项目类型")
    private String projectType;

    @Check(empty = true,description ="权益类型")
    private String rightsType;

    @Check(empty = true,description ="项目进度")
    private String projectSchedule;

    @Check(empty = true,description ="物业权重")
    private String propertyWeight;

    @Check(empty = true,description ="所在地区)")
    private String areaAddress;

    @Check(empty = true,description ="详细地址")
    private String detailAddress;

    @Check(empty = true,description ="联系人")
    private String contacts;

    @Check(empty = true,regexType = RegexType.PHONENUMBER,description ="联系电话")
    private String contactsTel;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="建筑面积")
    private BigDecimal buildArea;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="房间数/门店数")
    private Long roomsShopNumber;

    @Check(empty = true,regexType = RegexType.DECIMAL,description ="客单价(元)")
    private BigDecimal unitPrice;

    @Check(empty = true,description ="项目管理团队")
    private String manageTeamStr;

    @Check(empty = true,description ="平台项目管理团队")
    private String branchTeamStr;


    List<InputProjectADDManageDTO> manageDTOList;
    List<InputProjectADDBranchDTO> branchDTOList;
}
