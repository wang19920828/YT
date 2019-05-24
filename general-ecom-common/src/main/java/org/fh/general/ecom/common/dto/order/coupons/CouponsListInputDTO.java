package org.fh.general.ecom.common.dto.order.coupons;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CouponsListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String couponName;
    private String type;
    private String status;
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

}
