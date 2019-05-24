package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectTeamManageDetailDTO {
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Long age;
    /**
     * 职位
     */
    private String position;
    /**
     * 从业经验
     */
    private String practiceExperience;
}
