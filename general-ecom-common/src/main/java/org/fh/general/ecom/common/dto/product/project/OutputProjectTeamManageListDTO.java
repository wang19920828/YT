package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.po.product.project.ProjectTeamManageListOutputPO;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectTeamManageListDTO {

   private List<ProjectTeamManageListOutputPO> projectTeamManageListOutputPOList;
 }
