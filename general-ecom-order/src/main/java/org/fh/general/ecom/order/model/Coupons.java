package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@Data
@TableName("tb_coupons")
public class Coupons implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 优惠券名称
     */
	@TableField("coupon_name")
	private String couponName;
    /**
     * 优惠券类型:1-项目代金券 2-商品满减券 3-商品抵现券
     */
	private String type;
    /**
     * 已发放数量
     */
	@TableField("send_num")
	private Long sendNum;
    /**
     * 已领用数量
     */
	@TableField("receive_num")
	private Long receiveNum;
    /**
     * 状态  1-正常 2-过期
     */
	private String status;
    /**
     * 创建时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 抵用金额
     */
	@TableField("di_money")
	private BigDecimal diMoney;
    /**
     * 所需最低金额
     */
	@TableField("low_money")
	private BigDecimal lowMoney;
    /**
     * 券领用期-开始
     */
	@TableField("receive_start")
	private Date receiveStart;
    /**
     * 券领用期-结束
     */
	@TableField("receive_end")
	private Date receiveEnd;
    /**
     * 预计发放数量
     */
	@TableField("pre_send_num")
	private Long preSendNum;
    /**
     * 每人限领数量（默认1）
     */
	@TableField("limit_num")
	private Long limitNum;
    /**
     * 规则描述
     */
	private String rule;
    /**
     * 图片
     */
	private String image;
    /**
     * 领用后有效期（单位：天）
     */
	private Long days;
    /**
     * 平台编号：1001-云投
     */
	private String branch;
	/**
	 * 适用业务场景:1-注册 2-充值 3-订单支付成功
	 */
	@TableField("bus_scenario")
	private String busScenario;

	/**
	 * 企业编号
	 */
	@TableField("company_no")
	private String companyNo;
	/**
	 * 企业名称
	 */
	@TableField("company_name")
	private String companyName;

	/**
	 * 平台
	 */
	private String platform;
	/**
	 * 发放类型：1-领用 2-定向发放
	 */
	@TableField("send_type")
	private String sendType;
}
