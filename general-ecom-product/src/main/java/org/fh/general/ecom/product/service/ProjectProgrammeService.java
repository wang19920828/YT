package org.fh.general.ecom.product.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddProgrammeListDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectProgrammeListDTO;
import org.fh.general.ecom.common.dto.product.web.InputWebProjectProgrammePageDTO;
import org.fh.general.ecom.common.po.product.project.ProjectProgrammeListOutputPO;
import org.fh.general.ecom.product.model.ProjectProgramme;

import java.math.BigDecimal;

/**
 * <p>
 * 项目权益方案 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectProgrammeService extends IService<ProjectProgramme> {

    public boolean insertBatchList(InputProjectAddProgrammeListDTO dto);

    public OutputProjectProgrammeListDTO findList(Long projectId);

    public BigDecimal findTotalAmount(String projectId);

    public OutputProjectProgrammeListDTO findWebProjectProgrammePage(InputWebProjectProgrammePageDTO dto);

    public ProjectProgrammeListOutputPO findById(Long id);
}
