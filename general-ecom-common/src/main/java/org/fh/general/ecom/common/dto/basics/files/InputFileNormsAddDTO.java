package org.fh.general.ecom.common.dto.basics.files;

import lombok.Data;

/**
 * @Author huliping
 * @DATE 2018/9/28
 **/
@Data
public class InputFileNormsAddDTO {
    private String fileFlag;
    private Long fileSize;
    private Long fileWidth;
    private Long fileHeight;
    private String fileDepict;
    private String branch;
    private String channel;
    private String branchName;
}
