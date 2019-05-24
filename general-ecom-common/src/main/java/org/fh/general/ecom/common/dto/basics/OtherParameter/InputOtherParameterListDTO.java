package org.fh.general.ecom.common.dto.basics.OtherParameter;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.fh.general.ecom.common.valid.RegexType;

@Data
public class InputOtherParameterListDTO {
    @Check(empty = true, regexType = RegexType.NUMBER, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = true, regexType = RegexType.NUMBER, description = "分页大小")
    private Integer pageCount;

    private String branch;
}
