package org.fh.general.ecom.common.vo.product.project;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanEntityVO {

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
    private BigDecimal unitPrice;
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
    private BigDecimal minRedRate;
    /**
     * 投资预期收益率
     */
    private BigDecimal expectedReturnRate;
    /**
     * 方案描述
     */
    private String describe;
    /**
     * 赠送优惠券id，多个之间逗号隔开
     */
    private String couponId;


    private String total;
}
