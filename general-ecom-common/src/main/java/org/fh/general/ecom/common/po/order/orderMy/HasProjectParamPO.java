package org.fh.general.ecom.common.po.order.orderMy;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.List;

@Data
public class HasProjectParamPO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private Long userId;
    //1-筹资中；2-分红中；3-已完成；4-失败
    @Check(empty = false, description = "项目类型")
    private String projectFlag;
    private String projectStatusArr;
    private List<String> list;


}
