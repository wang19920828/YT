package org.fh.general.ecom.product.dao;

import org.fh.general.ecom.common.po.product.project.ProjectProgrammeListOutputPO;
import org.fh.general.ecom.product.model.ProjectProgramme;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 项目权益方案 Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectProgrammeDao extends BaseMapper<ProjectProgramme> {

    String  findTotalAmount(String projectId);

    List<ProjectProgrammeListOutputPO> findList(ProjectProgrammeListOutputPO projectProgrammeListOutputPO);
}