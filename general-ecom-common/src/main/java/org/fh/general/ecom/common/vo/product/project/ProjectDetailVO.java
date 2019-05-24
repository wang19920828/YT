package org.fh.general.ecom.common.vo.product.project;

import lombok.Data;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class ProjectDetailVO {

    /**
     * 项目主信息
     */
    private ProjectMainVO projectMainVo;
    /**
     * 管理团队
     */
    private List<ProjectTeamManageVO> projectTeamManageVOList;

    /**
     * 平台团队
     */
    private List<ProjectTeamBranchVO> projectTeamBranchVOList;
    /**
     * 项目详情
     */
    private ProjectDetailInfoVO  projectDetailInfoVO;
    /**
     * 众筹信息
     *
     */
    private ProjectFinancingVO  projectFinancingVO;
    /**
     * 方案列表
     */
    private List<ProjectProgrammeVO> projectProgrammeVOList;

    /**
     * 披露信息 - 公开
     */
    private List<ProjectFilesVO> projectFileVOList;

    /**
     *披露信息 - 投资人可见
     */
    private List<ProjectFilesVO> privateFileVOList;

}
