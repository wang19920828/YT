package org.fh.general.ecom.common.comm;

public class FdConstants {
	
	/**
	 * 状态购物返回
	 */
	public static final String status_shop = "SHOP";
	
	/**
	 * 状态请咖啡
	 */
	public static final String status_coffee = "COFFEE";   
	
	/**
	 * 一天的ms数
	 */
	public static final Long DAY =  86400000l;
	
	/**
	 * 福袋发放方式  对应 FdRecord  sendWay
	 */
	public static final String SEND_RANDOM = "RANDOM";  //积分随机
	
	public static final String SEND_UNKNOWN = "UNKNOWN"; //尚未选择
	
	public static final String SEND_AVERAGE = "AVERAGE"; //积分平均
	
	public static final String SEND_OWN = "OWN";  //全体自有
	
	public static final String SEND_ALLONE = "ALLONE";  //发给一人
	
	public static final String SEND_BACK = "BACK";  //未选择 退回
	
	public static final String SEND_YHQ_ONE = "YSENDO"; //优惠券赠送
	
	public static final String SEND_YHQ_MANY = "YSENDM"; //优惠券赠送
	
	public static final String SEND_COFFEE_MANY = "CSENDM"; //咖啡赠送
	
	public static final String SEND_COFFEE_ONE = "CSENDO"; //咖啡赠送
	
	/**
	 * 福袋领取种类 对应 FdReceive  drawType
	 */
	public static final String DRAW_OWN = "OWN";  //自有
	
	public static final String DRAW_SHARE = "SHARE"; //分享
	
	public static final String DRAW_ONE = "ONE"; //独享
	
	public static final String DRAW_BACK = "BACK"; //回退
	
	public static final String DRAW_SEND_YHQ = "YSEND"; //赠送
	
	public static final String DRAW_SEND_COFFEE = "CSEND"; //赠送
	
	/**
	 * 发放种类, 对应 FdReceive  sendType
	 */
	public static final String SEND_TYPE_YHQ = "YHQ";  //优惠券ID
	
	public static final String SEND_TYPE_SCORE = "SCORE"; //积分
	
	public static final String SEND_TYPE_COFFEE = "COFFEE"; //咖啡券ID
	
	public static final String SEND_COFFEE = "SCOFFEE"; //咖啡券ID赠送
	
	public static final String SEND_YHQ = "SYHQ";  //优惠券ID赠送

	/**
	 * 添加积分记录 记录类型为 抢福袋得带积分
	 */
	public static final String BUY_SCORE = "10";

	/**
	 * 对应优惠券平台
	 */
	public static final String PTDM = "000";

	/**
	 * 积分兑换比例所需参数 ----全时咖啡
	 */
	public static final String JFQD_COFFEE = "1";
	
	/**
	 * 咖啡券状态  CoffeeKey -- status
	 */
	public static final String UNUSE = "UNUSE"; //尚未使用
	
	public static final String USED = "USED";  //已使用
	
	
	/**
	 * 赠送 类型  优惠券 - 咖啡
	 */
	public static final String YHQ = "YHQ";
	
	/**
	 * 赠送 类型 - 咖啡赠送
	 */
	public static final String COFFEE_SEND = "CSEND";
	
	/**
	 * 赠送 类型 优惠券赠送
	 */
	public static final String YHQ_SEND = "YSEND";
	
	/**
	 * 赠送 类型  优惠券 - 咖啡
	 */
	public static final String COFFEE = "COFFEE";

	/**
	 * 赠送 状态 已送出  已接收  已退还
	 */
	public static final String SEND_OUT = "OUT";   
	
	public static final String DRAW_IN = "IN";   
	
	public static final String GO_BACK = "BACK";   
}
