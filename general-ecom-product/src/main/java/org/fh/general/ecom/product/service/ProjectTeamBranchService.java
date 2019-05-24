package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.project.InputProjectAddMainDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectTeamBranchListDTO;
import org.fh.general.ecom.product.model.ProjectTeamBranch;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目平台团队 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ProjectTeamBranchService extends IService<ProjectTeamBranch> {

    void insertBatchList(InputProjectAddMainDTO dto,Long projectId);

    OutputProjectTeamBranchListDTO findList(Long projectId);
}
