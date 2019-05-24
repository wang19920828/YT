package org.fh.general.ecom.common.dto.basics.files;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/28
 **/
@Data
public class InputFileNormsDetaiDTO {
    @Check(empty = true,description = "标志")
    private String fileFlag;
    @Check(empty = true,description = "平台")
    private String branch;
}
