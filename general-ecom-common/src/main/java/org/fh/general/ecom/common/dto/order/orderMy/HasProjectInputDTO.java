package org.fh.general.ecom.common.dto.order.orderMy;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class HasProjectInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    @Check(empty = false, description = "用户ID")
    private Long userId;
    //1-筹资中；2-分红中；3-已完成；4-失败
    @Check(empty = false, description = "项目类型")
    private String projectFlag;
}
