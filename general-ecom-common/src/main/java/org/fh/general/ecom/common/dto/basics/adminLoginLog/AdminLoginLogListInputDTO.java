package org.fh.general.ecom.common.dto.basics.adminLoginLog;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class AdminLoginLogListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
}
