package org.fh.general.ecom.common.vo.order.orderProduct;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderProductListVO {


    private Long id;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 子订单编号
     */
    private String orderSn;
    /**
     * 父订单编号
     */
    private Long parentSn;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 原单价（横线划掉的价格）
     */
    private BigDecimal oneMoney;
    /**
     * 现单价
     */
    private BigDecimal lessMoney;
    /**
     * 实付单价=less_money-平摊优惠
     */
    private BigDecimal unitMoney;
    /**
     * 数量
     */
    private String num;
    /**
     * 原价总=one_money*num
     */
    private BigDecimal oneTotal;
    /**
     * 现总价=less_money*num
     */
    private BigDecimal lessTotal;
    /**
     * 实付总价=unit_money*num
     */
    private BigDecimal unitTotal;
    /**
     * 优惠金额=less_total-unit_total
     */
    private BigDecimal shareAll;
    /**
     * 商品评价状态 0-未评价 1-已评价
     */
    private String status;
    /**
     * 通过审核：0、不通过；1；通过
     */
    private String audit;
    /**
     * 商品收货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date reciveTime;
    /**
     * 是否允许退换货: 0-否 1-是
     */
    private String isBarter;
    /**
     * cx类型: 0-商品打折;1-商品特价;2-商品赠送; 3-满减; 4-团购;5-商品加价购;6-整单换购;7-整单赠品
     */
    private String proType;
    /**
     * 退换货状态： 0-正常 1-退货 2-换货
     */
    private String barterStatus;
    /**
     * 积分兑换商品使用的积分
     */
    private Long scores;
    /**
     * 商品主图片
     */
    private String proImg;
    /**
     * 备注（酸甜苦辣）
     */
    private String productNote;
    /**
     * 1-堂食 2-外卖
     */
    private String isCook;
    /**
     * 包装费=单件包装费*num
     */
    private BigDecimal packingFee;
    /**
     * 原生商品ID
     */
    private String nativeId;
    /**
     * 是否属于预售券：  0-否  1-是预售券
     */
    private String isYsq;
    /**
     * 商品规格
     */
    private String guiGe;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除 0-否 1-是
     */
    private String isDelete;
    /**
     * 平台编号
     */
    private String branch;



}
