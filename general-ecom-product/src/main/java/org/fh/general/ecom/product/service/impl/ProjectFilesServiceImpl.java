package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddFileDTO;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddFilesListDTO;
import org.fh.general.ecom.common.dto.product.project.InputProjectFileListDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectFilesListDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.product.project.ProjectFilesListOutputPO;
import org.fh.general.ecom.product.dao.ProjectFilesDao;
import org.fh.general.ecom.product.model.ProjectFiles;
import org.fh.general.ecom.product.service.ProjectFilesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 项目披露信息表 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-18
 */
@Service
public class ProjectFilesServiceImpl extends ServiceImpl<ProjectFilesDao, ProjectFiles> implements ProjectFilesService {

    @Override
    public boolean insertBatchList(InputProjectAddFilesListDTO dto) {
        List<ProjectFiles> pubProjects = getProjectFiles(dto,  dto.getPublicList(), ProjectEnum.ProjectFileOpen.OPEN.getValue());
        List<ProjectFiles> privateProjects = getProjectFiles(dto, dto.getPrivateList(),ProjectEnum.ProjectFileOpen.CLOSED.getValue());

        List<ProjectFiles> list = new ArrayList<ProjectFiles>();
        list.addAll(pubProjects);
        list.addAll(privateProjects);
        if(list!=null && list.size()>0) {
            return insertBatch(list);
        }
        return false;
    }

    @Override
    public OutputProjectFilesListDTO findList(InputProjectFileListDTO dto) {
        OutputProjectFilesListDTO outputProjectFilesListDTO = new OutputProjectFilesListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("project_id",dto.getProjectId());
        wrapper.eq("open",dto.getOpen());
        List<ProjectFiles> list = baseMapper.selectList(wrapper);
        if(list!=null && list.size()>0) {
            List<ProjectFilesListOutputPO> listpo = new ArrayList<ProjectFilesListOutputPO>();
            list.forEach((ProjectFiles temp) -> {
                ProjectFilesListOutputPO po = new ProjectFilesListOutputPO();
                BeanUtils.copyProperties(temp, po);
                listpo.add(po);
            });
            outputProjectFilesListDTO.setProjectFileListOutputPOList(listpo);
            return outputProjectFilesListDTO;
        }
        return null;
    }

    private List<ProjectFiles> getProjectFiles(InputProjectAddFilesListDTO dto, List< InputProjectAddFileDTO > list,String open) {
        List<ProjectFiles> listProjects = new ArrayList<ProjectFiles>();
        if(list!=null && list.size()>0) {
            list.forEach((InputProjectAddFileDTO temp) -> {
                ProjectFiles projectFiles = new ProjectFiles();
                BeanUtils.copyProperties(temp, projectFiles);
                projectFiles.setProjectId(dto.getProjectId());
                projectFiles.setCreateDate(new Date());
                projectFiles.setCreateBy(dto.getCreateBy());
                projectFiles.setUpdateDate(new Date());
                projectFiles.setUpdateBy(dto.getUpdateBy());
                projectFiles.setOpen(open);
                projectFiles.setStatus(ComEnum.IsDelete.NORMAL.getValue());
                if(projectFiles.getId()!=null){
                    baseMapper.updateById(projectFiles);
                }else {
                    listProjects.add(projectFiles);
                }

            });
        }
            return listProjects;
     }
}
