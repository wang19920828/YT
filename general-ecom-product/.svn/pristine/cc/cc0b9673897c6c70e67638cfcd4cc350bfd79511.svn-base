package org.fh.general.ecom.product.service;

import org.fh.general.ecom.common.dto.product.project.InputProjectAddFilesListDTO;
import org.fh.general.ecom.common.dto.product.project.InputProjectFileListDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectFilesListDTO;
import org.fh.general.ecom.product.model.ProjectFiles;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目披露信息表 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
public interface ProjectFilesService extends IService<ProjectFiles> {

    boolean insertBatchList(InputProjectAddFilesListDTO dto);

    OutputProjectFilesListDTO findList(InputProjectFileListDTO inputProjectFileListDTO);
}
