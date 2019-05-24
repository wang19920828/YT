package org.fh.general.ecom.product.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.project.FinancingListInputDTO;
import org.fh.general.ecom.common.dto.product.project.FinancingListOutputDTO;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddFinancingDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectFinancingDTO;
import org.fh.general.ecom.common.po.product.project.ProjectFinancingOutputPO;
import org.fh.general.ecom.product.model.ProjectFinancing;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目筹资信息 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectFinancingService extends IService<ProjectFinancing> {

   public boolean insertFinancing(InputProjectAddFinancingDTO dto);
   public OutputProjectFinancingDTO findDetailByProjectId(Long projectId);
   public List<ProjectFinancingOutputPO> selectByParams(Map<String, Object> delayMap);
   public void updateList(Map<String, Object> delayMap);
  public FinancingListOutputDTO findFinancingList(FinancingListInputDTO dto);


}
