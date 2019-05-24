package org.fh.general.ecom.order.dao;

import org.fh.general.ecom.common.dto.order.projectFormula.SumOnePlanAmountInputDTO;
import org.fh.general.ecom.common.po.order.projectFormula.SumFormulaByPlanIdPO;
import org.fh.general.ecom.order.model.ProjectFormula;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
  * 分红试算/日历详情表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface ProjectFormulaDao extends BaseMapper<ProjectFormula> {

    SumFormulaByPlanIdPO sumFormulaByPlanId(String planId);

    BigDecimal  sumOnePlanAmount(SumOnePlanAmountInputDTO dto);

    ProjectFormula sumAmount(ProjectFormula projectFormula);
}