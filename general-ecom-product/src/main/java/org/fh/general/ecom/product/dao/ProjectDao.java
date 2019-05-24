package org.fh.general.ecom.product.dao;

import org.fh.general.ecom.common.po.product.project.ProjectListOutputPO;
import org.fh.general.ecom.product.model.Project;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 项目主信息 Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ProjectDao extends BaseMapper<Project> {

    List<ProjectListOutputPO> selectByStatus(Map<String, Object> map);

    int updateBatchStatus(Map<String, Object> map);

    List<String> findListByParams(Map<String, Object> map);



    List<ProjectListOutputPO> findData(ProjectListOutputPO po);
}