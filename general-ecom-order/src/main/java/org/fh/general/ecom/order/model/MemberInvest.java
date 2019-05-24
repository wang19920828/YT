package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 会员投资表（一个会员多次购买同一个项目的不同方案）；以项目为单位
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
public class MemberInvest implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 会员ID
     */
	private Long userId;
    /**
     * 会员姓名
     */
	private String userName;
    /**
     * 会员手机
     */
	private String phone;
    /**
     * 投资项目名称
     */
	private String projectName;
    /**
     * 项目唯一标识
     */
	private String project_Id;
    /**
     * 预约金额（元）（买多次就是多次的总和）
     */
	private BigDecimal amountAppointment;
    /**
     * 投资金额（元）（买多次就是多次的总和）
     */
	private BigDecimal amountInvest;
    /**
     * 累计发放分红（元）（买多次就是多次的总和）
     */
	private BigDecimal leijiShareAmount;
    /**
     * 扩展字段1
     */
	private String ext1;
    /**
     * 扩展字段2
     */
	private String ext2;

}
