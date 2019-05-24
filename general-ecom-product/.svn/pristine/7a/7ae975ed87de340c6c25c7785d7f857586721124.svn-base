package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.product.project.InputProjectADDBranchDTO;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddMainDTO;
import org.fh.general.ecom.common.dto.product.project.OutputProjectTeamBranchListDTO;
import org.fh.general.ecom.common.po.product.project.ProjectTeamBranchListOutputPO;
import org.fh.general.ecom.common.po.product.project.ProjectTeamManageListOutputPO;
import org.fh.general.ecom.product.dao.ProjectTeamBranchDao;
import org.fh.general.ecom.product.model.ProjectTeamBranch;
import org.fh.general.ecom.product.service.ProjectTeamBranchService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 项目平台团队 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Service
@Transactional
public class ProjectTeamBranchServiceImpl extends ServiceImpl<ProjectTeamBranchDao, ProjectTeamBranch> implements ProjectTeamBranchService {
    @Override
    public void insertBatchList(InputProjectAddMainDTO dto, Long projectId) {
        List<InputProjectADDBranchDTO> list = dto.getBranchDTOList();
        if (list != null && list.size() > 0) {
            List<ProjectTeamBranch> branchList = new ArrayList<ProjectTeamBranch>();
            list.forEach((InputProjectADDBranchDTO temp) -> {
                ProjectTeamBranch branch = new ProjectTeamBranch();
                BeanUtils.copyProperties(temp, branch);
                branch.setCreateDate(new Date());
                branch.setUpdateBy(dto.getCreateBy());
                branch.setUpdateDate(new Date());
                branch.setProjectId(projectId);
                if(branch.getId()!=null){
                    baseMapper.updateById(branch);
                }else {
                    branchList.add(branch);
                }
            });
            super.insertBatch(branchList);
        }
    }

    @Override
    public OutputProjectTeamBranchListDTO findList(Long projectId) {
        OutputProjectTeamBranchListDTO outputProjectTeamBranchListDTO = new OutputProjectTeamBranchListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("project_id",projectId);
        List<ProjectTeamBranch> list = this.baseMapper.selectList(wrapper);
        if(list!=null &&  list.size()>0) {
            List<ProjectTeamBranchListOutputPO> listpo = new ArrayList<ProjectTeamBranchListOutputPO>();
            list.forEach((ProjectTeamBranch temp) -> {
                ProjectTeamBranchListOutputPO po = new ProjectTeamBranchListOutputPO();
                BeanUtils.copyProperties(temp, po);
                listpo.add(po);
            });
            outputProjectTeamBranchListDTO.setProjectTeamBranchListOutputPOList(listpo);
            return outputProjectTeamBranchListDTO;
        }
        return null;
    }


}
