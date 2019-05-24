package org.fh.general.ecom.common.po.order.order;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class OrderListParamPO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    /**
     * 订单编号或者项目名称
     */
    private String orderOrProject;
    /**
     * 投资人姓名
     */
    private String userNameOrTel;
    /**
     * 订单类型: 0-预约 1-预约认购 2-公开认购
     */
    private String orderType;

    /**
     * 开始时间
     */
    private String addTimeStart;

    /**
     * 结束时间
     */
    private String addTimeEnd;

    /**
     * 订单状态:1-正常 2-预约未申购 3-认购失败 4-冷静期退出
     */
    private String orderStatus;
    private String productId;
    private String payStatus;
    //其他地方用(查询订单不分页的时候会用到)
    private String planId;
    private String noCompleteTime;

}
