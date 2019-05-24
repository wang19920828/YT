package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 优惠券发放记录表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_coupons_log")
public class CouponsLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 优惠券名称
     */
	private String coupon;
    /**
     * 发放类型：1-领用 2-定向发放
     */
	private String type;
    /**
     * 发放数量
     */
	private Long num;
    /**
     * 用户手机号
     */
	private String phone;
    /**
     * 适用业务场景:1-注册 2-充值 3-订单支付成功
     */
	@TableField("bus_scenario")
	private String busScenario;
	@TableField("add_time")
	private Date addTime;


}
