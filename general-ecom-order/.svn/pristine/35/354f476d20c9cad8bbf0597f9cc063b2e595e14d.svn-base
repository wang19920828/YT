package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 优惠码表
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@Data
@TableName("tb_coupons_code")
public class CouponsCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 优惠码
     */
	private String yhm;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 优惠券名称
     */
	@TableField("coupon_name")
	private String couponName;
    /**
     * 优惠券编码
     */
	@TableField("coupons_id")
	private Long couponsId;
    /**
     * 发放类型：1-领用 2-定向发放
     */
	@TableField("send_type")
	private String sendType;
    /**
     * 使用业务场景：1-注册 2-投资
     */
	private String situation;

    /**
     * 失效时间
     */
	@TableField("off_time")
	private Date offTime;
    /**
     * 使用状态：1-未使用 2-已使用
     */
	private String status;
    /**
     * 发放记录号
     */
	@TableField("record_no")
	private String recordNo;
    /**
     * 领用用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 创建人
     */
	@TableField("user_name")
	private String userName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;



}
