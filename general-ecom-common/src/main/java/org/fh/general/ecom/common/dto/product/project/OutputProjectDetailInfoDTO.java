package org.fh.general.ecom.common.dto.product.project;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectDetailInfoDTO {

    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 项目详情
     */
    private String projectDetail;


    private String  pcHeaderImages;

    private String  appHeaderImages;
    /**
     * 方案详情
     */
    private String programmeDetail;
    /**
     * 额外福利
     */
    private String extraBenefits;
    /**
     * 退出机制
     */
    private String exitMechanism;
    /**
     * 风险控制
     */
    private String windControlMeasures;
}
