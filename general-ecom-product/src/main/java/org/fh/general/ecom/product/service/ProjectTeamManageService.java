package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.project.InputProjectAddMainDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectTeamManageListDTO;
import org.fh.general.ecom.product.model.ProjectTeamManage;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 项目管理团队信息 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ProjectTeamManageService extends IService<ProjectTeamManage> {

    void insertBatchList(InputProjectAddMainDTO manageList, Long id);

    OutputProjectTeamManageListDTO findList(Long id);
}
