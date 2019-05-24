package org.fh.general.ecom.common.dto.product.appraise;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/21
 **/

@Data
public class InputProjectAppraiseListDTO {

    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageSize;
    @Check(empty = true ,description = "项目信息")
    private String projectId;
    @Check(empty = true ,description = "项目名称")
    private String projectName;
    @Check(empty = true ,description = "用户id")
    private String createBy;
    @Check(empty = true ,description = "手机号")
    private String phone;
    @Check(empty = true ,description = "平台")
    private String branch;
    @Check(empty = true ,description = "开始时间")
    private String startTimeStr;
    @Check(empty = true ,description = "结束时间")
    private String endTimeStr;


    private String  appraiseType;



}
