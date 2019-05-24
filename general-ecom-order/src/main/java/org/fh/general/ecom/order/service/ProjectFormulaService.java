package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.projectFormula.FindFoumulListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.JiSuanShareRedAmountInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListOutputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.SumOnePlanAmountInputDTO;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.order.projectFormula.SumFormulaByPlanIdPO;
import org.fh.general.ecom.common.po.product.order.OrderCountListOutPO;
import org.fh.general.ecom.order.model.ProjectFormula;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.Formatter;
import java.util.List;

/**
 * <p>
 * 分红试算/日历详情表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface ProjectFormulaService extends IService<ProjectFormula> {

    ProjectFormulaListOutputDTO findPage(ProjectFormulaListInputDTO dto)throws Exception;

    String addEntity(ProjectFormulaAddInputDTO dto)throws Exception;

    SumFormulaByPlanIdPO sumFormulaByPlanId(String planId);

    BigDecimal jiSuanShareRedAmount(JiSuanShareRedAmountInputDTO dto);

    BigDecimal sumOnePlanAmount(SumOnePlanAmountInputDTO dto);

    BigDecimal findEntity(OrderCountListOutPO dto);

    List<ProjectFormula> findList(FindFoumulListInputDTO dto);
}
