package org.fh.general.ecom.product.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.project.*;
import org.fh.general.ecom.common.dto.product.web.InputWebProjectDetailDTO;
import org.fh.general.ecom.common.dto.product.web.OutputWebProjectDetailAllDTO;
import org.fh.general.ecom.product.model.Project;

import java.math.BigDecimal;

/**
 * <p>
 * 项目主信息 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
public interface ProjectService extends IService<Project> {

    public OutPutProjectAddDTO addProjectMain(InputProjectAddMainDTO dto);

    public OutputProjectListDTO findPage(InputProjectListDTO dto);

    public OutPutProjectAddDTO addProjectDetail(InputProjectAddDetailDTO dto);

    public OutPutProjectAddDTO addProjectFinancing(InputProjectAddFinancingDTO dto);

    public OutPutProjectAddDTO addProjectProgrammes(InputProjectAddProgrammeListDTO dto);

    public OutPutProjectAddDTO addProjectFiles(InputProjectAddFilesListDTO dto);

    public OutputProjectDetailDTO findDetail(InputProjectDetailDTO dto);

    public String updateDetail(InputProjectUpdateDTO dto);

    public OutPutProjectAddDTO addAllInfo(InputProjectAllAddDTO dto);

    public OutPutProjectAddDTO updateStatus(InputProjectUpdateStatusDTO dto);

    public OutputWebProjectDetailAllDTO findWebDetail(InputWebProjectDetailDTO dto);

    public BigDecimal  findFinishedAmount(String projectStatus,String projectId);

    public ProjectOneDetailDTO findDetailByProjectId(String projectId);

    public OutputProjectMainInfoDTO findMainInfoByProjectId(String projectId);


    //***定时任务*//
    public void  taskRun();
}
