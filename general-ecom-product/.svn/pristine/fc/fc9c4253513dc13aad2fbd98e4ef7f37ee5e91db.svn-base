package org.fh.general.ecom.product.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.consulting.*;
import org.fh.general.ecom.product.model.ConsultingProject;

/**
 * <p>
 * 咨询项目表 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ConsultingProjectService extends IService<ConsultingProject> {

    String addConsulting(InputConsultingProjectAddDTO dto);

    OutputConsultingProjectListDTO findPage(InputConsultingProjectListDTO dto);

    OutputConsultingProjectDetailDTO findEntity(String id);

    OutputConsultingProjectDetailDTO findDetailByProjectId(String projectId);

    String updateConsultingProject(InputConsultingProjectUpdateDTO dto);

    String delConsultingProject(InputConsultingProjectDelDTO dto);
}
