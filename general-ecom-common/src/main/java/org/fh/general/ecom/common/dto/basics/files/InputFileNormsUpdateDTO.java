package org.fh.general.ecom.common.dto.basics.files;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/28
 **/
@Data
public class InputFileNormsUpdateDTO {
    @Check(empty = true,description = "id")
    private Long id;
    private String fileFlag;
    private Long fileSize;
    private Long fileWidth;
    private Long fileHeight;
    private String fileDepict;
}
