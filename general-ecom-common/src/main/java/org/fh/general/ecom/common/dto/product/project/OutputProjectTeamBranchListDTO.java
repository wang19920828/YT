package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.po.product.project.ProjectTeamBranchListOutputPO;
import org.fh.general.ecom.common.valid.Check;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectTeamBranchListDTO {

    private List<ProjectTeamBranchListOutputPO> projectTeamBranchListOutputPOList;
}
