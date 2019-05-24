package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListInputDTO;
import org.fh.general.ecom.common.dto.order.projectPlan.ProjectPlanListOutputDTO;
import org.fh.general.ecom.order.model.ProjectPlan;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目分红方案表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface ProjectPlanService extends IService<ProjectPlan> {





    ProjectPlanListOutputDTO findPage(ProjectPlanListInputDTO dto)throws Exception;


    String addEntity(ProjectPlanAddInputDTO dto)throws Exception;
}
