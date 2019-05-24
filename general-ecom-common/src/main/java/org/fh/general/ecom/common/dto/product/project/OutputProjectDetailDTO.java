package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.dto.product.projectdetail.*;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectDetailDTO {


    /**
     * 项目主信息
     */
    private ProjectMainDTO projectMainDTO;
    /**
     * 管理团队
     */
    private List<ProjectTeamManageDTO> projectTeamManageDTOList;

    /**
     * 平台团队
     */
    private List<ProjectTeamBranchDTO> projectTeamBranchDTOList;
    /**
     * 项目详情
     */
    private ProjectDetailInfoDTO projectDetailInfoDTO;
    /**
     * 众筹信息
     *
     */
    private ProjectFinancingDTO projectFinancingDTO;
    /**
     * 方案列表
     */
    private List<ProjectProgrammeDTO> projectProgrammeDTOList;

    /**
     * 披露信息 - 公开
     */
    private List<ProjectFilesDTO> projectFileDTOList;

    /**
     *披露信息 - 投资人可见
     */
    private List<ProjectFilesDTO> privateFileDTOList;

}
