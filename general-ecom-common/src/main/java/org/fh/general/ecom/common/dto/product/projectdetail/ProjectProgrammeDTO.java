package org.fh.general.ecom.common.dto.product.projectdetail;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class ProjectProgrammeDTO {
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;


    private String projectName;

    /**
     * 方案名称
     */
    private String programmeName;
    /**
     * 投资单价
     */
    private String  unitPrice;
    /**
     * 可预约份数(总)
     */
    private Long number;
    /**
     * 预约限购数（每人）
     */
    private Long limitNumber;
    /**
     * 最低分红收益率
     */
    private String minRedRate;
    /**
     * 投资预期收益率
     */
    private String expectedReturnRate;
    /**
     * 方案描述
     */
    private String describe;
    /**
     * 赠送优惠券id，多个之间逗号隔开
     */
    private String couponId;


    /**
     * 已经预约份数
     */
    private String total;

    private String schedule; //预约进度

    private String couponName;


}
