package org.fh.general.ecom.common.dto.product.consulting;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * 列表查询 页面发送请求时的对象实体
 * @Author huliping
 * @DATE 2018/9/13
 **/
@Data
public class InputConsultingProjectListDTO {

    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageCount;
    @Check(empty = true ,description = "状态")
    private String status;
    @Check(empty = true ,description = "企业名称")
    private String projectName;
    @Check(empty = true ,description = "联系人")
    private String contacts;
    @Check(empty = true ,regexType = RegexType.PHONENUMBER, description = "联系电话")
    private String contactsTel;
    @Check(empty = true ,description = "城市")
    private String registeredAddress;


}
