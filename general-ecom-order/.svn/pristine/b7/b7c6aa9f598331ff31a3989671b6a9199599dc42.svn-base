package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.redProject.BatchInsertRedProjectInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplySubmitInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectAddInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListOutputDTO;
import org.fh.general.ecom.order.model.RedProject;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 分红项目管理表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface RedProjectService extends IService<RedProject> {

    RedProjectListOutputDTO findPage(RedProjectListInputDTO dto)throws Exception;

    String addEntity(RedProjectAddInputDTO dto)throws Exception;

    RedProjectBgDetailOutputDTO detailBgRedProject(Long id)throws Exception;

    RedApplyTryOutputDTO redApplyTry(RedApplyTryInputDTO dto);

    String redApplySubmit(RedApplySubmitInputDTO dto);

    String batchInsertRedProject(BatchInsertRedProjectInputDTO dot);

}
