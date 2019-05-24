package org.fh.general.ecom.common.dto.product.web;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class InputWebProjectDetailDTO {

    private Long  id ;

    //热门项目使用 当前页面
    private Integer currentPageNum;
    //热门项目使用 每页记录数
    private Integer pageSize;
}
