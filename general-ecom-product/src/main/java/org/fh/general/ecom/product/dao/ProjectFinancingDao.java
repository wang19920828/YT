package org.fh.general.ecom.product.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.fh.general.ecom.common.po.product.project.FinancingListInputPO;
import org.fh.general.ecom.common.po.product.project.ProjectFinancingOutputPO;
import org.fh.general.ecom.product.model.ProjectFinancing;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 项目筹资信息 Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectFinancingDao extends BaseMapper<ProjectFinancing> {

    List<ProjectFinancingOutputPO> selectByParams(Map<String, Object> delayMap);

    List<ProjectFinancing> findFinancingList( FinancingListInputPO paramPO);

    void updateByList(Map<String, Object> delayMap);

}