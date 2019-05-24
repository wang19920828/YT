package org.fh.general.ecom.common.dto.basics.files;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

/**
 * @Author huliping
 * @DATE 2018/9/28
 **/
@Data
public class InputFileNormsListDTO {
    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageCount;
    @Check(empty = true,description = "平台")
    private String branch;
}
