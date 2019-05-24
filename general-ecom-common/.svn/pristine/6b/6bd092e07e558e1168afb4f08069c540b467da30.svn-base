package org.fh.general.ecom.common.dto.product.projectlog;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class InputProjectOperLogListDTO {
    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageSize;
    @Check(empty = true ,description = "项目id")
    private Long projectId;


}
