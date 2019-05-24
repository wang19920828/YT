package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.consulting.InputConsultingProjectAddDTO;
import org.fh.general.ecom.common.dto.product.consulting.OutputConsultingProjectListDTO;
import org.fh.general.ecom.common.dto.product.consulting.OutputConsultingProjectTeamListDTO;
import org.fh.general.ecom.product.model.ConsultingProjectTeam;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 咨询项目管理团队信息 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ConsultingProjectTeamService extends IService<ConsultingProjectTeam> {

    public void insertBatchList(InputConsultingProjectAddDTO dto,Long projectId);

    OutputConsultingProjectTeamListDTO findList(String id);
}
