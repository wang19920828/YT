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
 * 订单信息表
 * </p>
 *
 * @author pjj
 * @since 2018-08-09
 */
@Data
@TableName("tb_invest_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId(value="order_id", type= IdType.AUTO)
	private Long orderId;
	/**
	 * 订单编号
	 */
	@TableField("order_sn")
	private String orderSn;

	/**
	 * 项目名称
	 */
	@TableField("project_name")
	private String projectName;
	/**
	 * 投资金额
	 */
	@TableField("invest_money")
	private BigDecimal investMoney;

	/**
	 * 订单应付金额
	 */
	@TableField("org_price")
	private BigDecimal orgPrice;
	/**
	 * 订单优惠金额
	 */
	@TableField("share_all")
	private BigDecimal shareAll;

	/**
	 * 预约款
	 */
	@TableField("yuyue_price")
	private BigDecimal yuyuePrice;

	/**
	 * 订单实付金额=org_price-share_all
	 */
	@TableField("all_price")
	private BigDecimal allPrice;
	/**
	 * 下单用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 下单用户名称
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 下单用户手机号
	 */
	@TableField("user_phone")
	private String userPhone;



	/**
	 * 1-正常 2-预约未申购 3-认购失败 4-冷静期退出  5-已失效  6-认购成功
	 * 预约未申购
	 * 情况1：预约单，没找到预约认购单
	 * 情况2：预约单，找到了预约认购单，但是认购单是认购失败状态
	 */
	@TableField("order_status")
	private String orderStatus;
	/**
	 * 支付状态：0-未支付 1-已支付 2-申请退款 3-已退款
	 */
	@TableField("pay_status")
	private String payStatus;
	/**
	 * 收货人姓名
	 */
	@TableField("accept_name")
	private String acceptName;
	/**
	 * 收货人手机
	 */
	@TableField("accept_phone")
	private String acceptPhone;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 预约单订单号
	 */
	private String oldcode;
	/**
	 * 说明
	 */
	private String postscript;
	/**
	 * 支付方式：1-微信支付、2-QQ钱包支付、3-支付宝支付、4-电子钱包支付、5-电子卡支付 6-券兑换 7-平台支付 8-京东到家 9-美团 10-百度 11-人民币  13-理财账户 14- 其他：如活动推广 15-红包
	 */
	@TableField("pay_type")
	private String payType;
	/**
	 * 支付方式说明
	 */
	@TableField("pay_name")
	private String payName;
	/**
	 * 下单时间
	 */
	@TableField("add_time")
	private Date addTime;
	/**
	 * 支付时间
	 */
	@TableField("pay_time")
	private Date payTime;
	/**
	 * 完成时间
	 */
	@TableField("complete_time")
	private Date completeTime;
	/**
	 * 订单类型: 0-预约 1-预约认购 2-公开认购
	 */
	@TableField("order_type")
	private String orderType;
	/**
	 * 订单来源:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
	 */
	private String channel;
	/**
	 * 是否删除（0-否 1-是）
	 */
	@TableField("is_del")
	private String isDel;
	/**
	 * 平台编号：1001-D5厨房1002-美食工场 1003-爱炉火锅 1004-真真小吃 1005-健康体检  1006-咖啡 1007-花酷 1008-竹棠里 1009-海鲜滋色
	 */
	private String branch;
	/**
	 * 平台名称
	 */
	@TableField("branch_name")
	private String branchName;
	/**
	 * 项目ID
	 */
	@TableField("product_id")
	private String productId;
	/**
	 * 方案ID
	 */
	@TableField("plan_id")
	private Long planId;

	private String email;
}
