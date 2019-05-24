package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.project.InputProjectAddDetailDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectDetailInfoDTO;
import org.fh.general.ecom.product.model.ProjectDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;

/**
 * <p>
 * 项目详情表 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectDetailService extends IService<ProjectDetail> {


    boolean insertDetail(InputProjectAddDetailDTO dto);

    OutputProjectDetailInfoDTO findDetailByProjectId(Long projectId);
}
