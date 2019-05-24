package org.fh.general.ecom.product.dao;

import org.fh.general.ecom.common.po.product.appraise.InputProjectAppraisePO;
import org.fh.general.ecom.common.po.product.appraise.OutputProjectAppraiseDetailPO;
import org.fh.general.ecom.product.model.ProjectAppraise;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 项目评价信息 Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2018-09-21
 */
public interface ProjectAppraiseDao extends BaseMapper<ProjectAppraise> {

    List<OutputProjectAppraiseDetailPO> findList(InputProjectAppraisePO inputProjectAppraisePO);
}