package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectAddDetailDTO {

    @Check(empty = false ,description ="项目id")
    private Long  projectId;

    @Check(empty = true,description ="项目摘要")
    private String projectSummary;

    @Check(empty = true,description ="宣传图片pc")
    private String pcImageUrl;

    @Check(empty = true,description ="宣传图片app")
    private String appImageUrl;

    @Check(empty = true,description ="项目详情")
    private String projectDetail;

    @Check(empty = true,description ="投资权益介绍")
    private String programmeDetail;

    @Check(empty = true,description ="额外福利")
    private String extraBenefits;

    @Check(empty = true,description ="退出机制")
    private String exitMechanism;

    @Check(empty = true,description ="风险控制")
    private String windControlMeasures;

    @Check(empty = true,description ="修改用户")
    private String updateBy;

    @Check(empty = true,description ="项目头图pc")
    private String pcHeaderImages;

    @Check(empty = true,description ="项目头图app")
    private String appHeaderImages;

    private String createBy;

    private String channel;

    private String projectStatus;


}
