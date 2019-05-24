package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.appraise.*;
import org.fh.general.ecom.product.model.ProjectAppraise;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目评价信息 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-21
 */
public interface ProjectAppraiseService extends IService<ProjectAppraise> {

    public OutputProjectAppraiseListDTO findPage(InputProjectAppraiseListDTO dto);

    public  OutputProjectAppriaiseDetailDTO findById(String id);

    public String delById(InputProjectAppraiseDetailDTO dto);

    public String updateDetail(InputProjectAppraiseDetailDTO dto);

    public String addEntity(InputProjectAppraiseAddDTO dto);
}
