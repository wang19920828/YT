package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class InputProjectListDTO {
    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageCount;
    @Check(empty = true ,description = "状态")
    private String status;
    @Check(empty = true ,description = "项目名称")
    private String  projectName;
    @Check(empty = true ,description = "项目类型")
    private String  projectType;
    @Check(empty = true ,description = "权益类型")
    private String  rightsType;
    @Check(empty = true ,description = "平台")
    private String branch;
    @Check(empty = true ,description = "渠道")
    private String channel;

}
