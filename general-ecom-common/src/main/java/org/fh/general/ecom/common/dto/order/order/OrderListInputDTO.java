package org.fh.general.ecom.common.dto.order.order;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class OrderListInputDTO {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addTimeStart;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addTimeEnd;

    /**
     * 订单状态:1-正常 2-预约未申购 3-认购失败 4-冷静期退出
     */
    private String orderStatus;


    private String projectId ;

}
