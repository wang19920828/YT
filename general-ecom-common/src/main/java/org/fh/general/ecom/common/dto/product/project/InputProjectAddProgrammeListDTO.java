package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectAddProgrammeListDTO
{

    private Long id ;

    @Check(empty = false,description = "项目id")
    private Long projectId;
    @Check(empty = false,description = "权益方案信息")
    private String programmeStr;

    private List<InputProjectAddProgrammeDTO> list;

    private String branch;

    private String branchName;

    private String createBy;

    private String updateBy;

    private String projectStatus;
}
