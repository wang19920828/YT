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
 * 订单产品关系表
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@Data
@TableName("tb_invest_product")
public class OrderProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 产品ID
	 */
	@TableField("product_id")
	private String productId;
	/**
	 * 子订单编号
	 */
	@TableField("order_sn")
	private String orderSn;
	/**
	 * 用户ID
	 */
	@TableField("user_id")
	private String userId;
	/**
	 * 项目名称
	 */
	@TableField("product_name")
	private String productName;
	/**
	 * 项目详情
	 */
	@TableField("product_detail")
	private String productDetail;
	/**
	 * 方案
	 */
	@TableField("product_plan")
	private String productPlan;
	/**
	 * 方案ID
	 */
	@TableField("plan_id")
	private Long planId;
	/**
	 * 原单价（横线划掉的价格）
	 */
	@TableField("one_money")
	private BigDecimal oneMoney;
	/**
	 * 现单价
	 */
	@TableField("less_money")
	private BigDecimal lessMoney;
	/**
	 * 实付单价=less_money-平摊优惠
	 */
	@TableField("real_money")
	private BigDecimal realMoney;
	/**
	 * 数量
	 */
	private String num;
	/**
	 * 原价总=one_money*num
	 */
	@TableField("one_total")
	private BigDecimal oneTotal;
	/**
	 * 现总价=less_money*num
	 */
	@TableField("less_total")
	private BigDecimal lessTotal;
	/**
	 * 实付总价=real_money*num
	 */
	@TableField("real_total")
	private BigDecimal realTotal;
	/**
	 * 优惠金额=less_total-real_total
	 */
	@TableField("share_all")
	private BigDecimal shareAll;
	/**
	 * 商品主图片
	 */
	@TableField("pro_img")
	private String proImg;
	/**
	 * cx类型: 0-普通商品;1-商品特价;
	 */
	@TableField("pro_type")
	private String proType;
	/**
	 * 积分兑换商品使用的积分
	 */
	private Long scores;
	/**
	 * 备注（酸甜苦辣）
	 */
	@TableField("product_note")
	private String productNote;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否删除 0-否 1-是
	 */
	@TableField("is_delete")
	private String isDelete;
	/**
	 * 平台编号
	 */
	private String branch;

	@TableField("order_type")
	private String orderType;

	/**
	 * 优惠码
	 */
	private String yhm;
	/**
	 * 代金券编号
	 */
	@TableField("ticket_sn")
	private String ticketSn;
}
