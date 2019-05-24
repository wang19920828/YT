package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectAddFilesListDTO {

    @Check(empty = false,description = "项目信息")
    private Long projectId;

    @Check(empty = false,description = "公开信息")
    private String  publicInfos;
    @Check(empty = false,description ="投资人可见")
    private String privateInfos;

    private List<InputProjectAddFileDTO> publicList;
    private List<InputProjectAddFileDTO> privateList;

    private String createBy;

    private String updateBy;

    private String branch;

    private String branchName;

}
