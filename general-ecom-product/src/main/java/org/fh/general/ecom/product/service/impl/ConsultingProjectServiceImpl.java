package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.comm.ProjectConstants;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.product.consulting.*;
import org.fh.general.ecom.common.dto.product.project.InputProjectAddMainDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.product.consulting.ConsultingProjectOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.client.BasicClient;
import org.fh.general.ecom.product.dao.ConsultingProjectDao;
import org.fh.general.ecom.product.model.ConsultingProject;
import org.fh.general.ecom.product.service.ConsultingProjectService;
import org.fh.general.ecom.product.service.ConsultingProjectTeamService;
import org.fh.general.ecom.product.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 咨询项目表 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Slf4j
@Service
public class ConsultingProjectServiceImpl extends ServiceImpl<ConsultingProjectDao, ConsultingProject> implements ConsultingProjectService {
    @Autowired
    private ConsultingProjectTeamService consultingProjectTeamService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private BasicClient userClient;
    @Override
    public String addConsulting(InputConsultingProjectAddDTO dto) {

        ConsultingProject consultingProject = new ConsultingProject();
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.where("status !={0}",ComEnum.IsDelete.DEL.getValue())
                .andNew("project_name={0}",dto.getProjectName());
        int i =  baseMapper.selectCount(wrapper);
        if(i>0){
            return OutEnum.TIPS.getCode();
        }
        BeanUtils.copyProperties(dto,consultingProject);
        consultingProject.setCreateDate(new Date());
        consultingProject.setUpdateBy(dto.getCreateBy());
        consultingProject.setUpdateDate(new Date());
        consultingProject.setStatus(ComEnum.IsDelete.NORMAL.getValue());
        consultingProject.setBranch(dto.getBranch()==null?"1001":dto.getBranch());
        consultingProject.setBranchName(dto.getBranchName()==null?"云投":dto.getBranchName());
        consultingProject.setChannel(dto.getChannel()==null?"0":dto.getChannel());
        int success= baseMapper.insert(consultingProject);
        if(success>0){
            consultingProjectTeamService.insertBatchList(dto,consultingProject.getId());
            InputProjectAddMainDTO projectAddDTO  =new InputProjectAddMainDTO();
            BeanUtils.copyProperties(dto,projectAddDTO);
            projectAddDTO.setProjectSource(ProjectEnum.ProjectSource.PC.getValue());
            projectAddDTO.setConsultingId(consultingProject.getId()+"");
            projectService.addProjectMain(projectAddDTO);
            return OutEnum.SUCCESS.getCode();
         }
        return  OutEnum.FAIL.getCode();
    }

    @Override
    public OutputConsultingProjectListDTO findPage(InputConsultingProjectListDTO dto) {
        OutputConsultingProjectListDTO outputConsultingProjectListDTO= new OutputConsultingProjectListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getContacts())){
            wrapper.like("contacts",dto.getContacts());
        }

        if(StringUtils.isNotEmpty(dto.getContactsTel())){
            wrapper.eq("contacts_tel",dto.getContactsTel());
        }

        if(StringUtils.isNotEmpty(dto.getRegisteredAddress())){
            wrapper.eq("registered_address",dto.getRegisteredAddress());
        }
        List<ConsultingProject> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<ConsultingProjectOutputPO>  listpo=new ArrayList<ConsultingProjectOutputPO>();
        list.forEach((ConsultingProject temp) -> {
            ConsultingProjectOutputPO po=new ConsultingProjectOutputPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        outputConsultingProjectListDTO.setList(listpo);
        outputConsultingProjectListDTO.setPageInfo(pageInfo);
        return outputConsultingProjectListDTO;
    }

    @Override
    public OutputConsultingProjectDetailDTO findEntity(String id) {
        OutputConsultingProjectDetailDTO response = new OutputConsultingProjectDetailDTO();
        ConsultingProject consultingProject  = baseMapper.selectById(id);
        if(consultingProject==null){
            return null;
        }
        BeanUtils.copyProperties(consultingProject,response);

        OutputDictionaryDetailDTO outputDictionaryDetailDTO = findDictionaryInfo(consultingProject.getProjectType(), ProjectConstants.DICTIONARY_PROJECT_TYPE);
        if(outputDictionaryDetailDTO!=null){
            response.setProjectTypeName(outputDictionaryDetailDTO.getLabel());
        }
        outputDictionaryDetailDTO = findDictionaryInfo(consultingProject.getProjectSchedule(),ProjectConstants.DICTIONARY_PROJECT_SCHEDULE);
        if(outputDictionaryDetailDTO!=null){
            response.setProjectScheduleName(outputDictionaryDetailDTO.getLabel());
        }

        outputDictionaryDetailDTO = findDictionaryInfo(consultingProject.getProjectSchedule(),ProjectConstants.DICTIONARY_PROPERTY_WEIGHT);
        if(outputDictionaryDetailDTO!=null){
            response.setPropertyWeightName(outputDictionaryDetailDTO.getLabel());
        }
        String name = this.userClient.findbySortCode(consultingProject.getAreaAddress());
        response.setAreaAddressName(name);

        response.setChannel(ProjectEnum.ProjectSource.codeOf(consultingProject.getChannel()).getName());
        return response;
    }

    private OutputDictionaryDetailDTO findDictionaryInfo(String value, String type) {
        InputDictionaryQueryDTO requestDto = new InputDictionaryQueryDTO();
        requestDto.setValue(value);
        requestDto.setType(type);
        return this.userClient.findLabelByValueAndType(requestDto);
    }

    @Override
    public OutputConsultingProjectDetailDTO findDetailByProjectId(String projectId) {
        OutputConsultingProjectDetailDTO response = new OutputConsultingProjectDetailDTO();
        ConsultingProject project = new  ConsultingProject();
        project.setProjectId(projectId);
        ConsultingProject consultingProject  = baseMapper.selectOne(project);
        BeanUtils.copyProperties(consultingProject,response);
        return response;
    }

    @Override
    public String updateConsultingProject(InputConsultingProjectUpdateDTO dto) {
        ConsultingProject entity = baseMapper.selectById(dto.getId());
        if(entity==null){
            return OutEnum.SUCCESS.getCode();
        }
        ConsultingProject consultingProject=new ConsultingProject();
        BeanUtils.copyProperties(dto,consultingProject);

        consultingProject.setCreateBy(entity.getCreateBy());
        consultingProject.setBranch(entity.getBranch());
        consultingProject.setBranchName(entity.getBranchName());
        int success= baseMapper.updateById(consultingProject);

        if(success<=0){
            return OutEnum.FAIL.getCode();
        }
        return  OutEnum.SUCCESS.getCode();
    }

    @Override
    public String delConsultingProject(InputConsultingProjectDelDTO dto) {
        ConsultingProject consultingProject = baseMapper.selectById(dto.getId());
        if(consultingProject==null){
            return OutEnum.SUCCESS.getCode();
        }
        consultingProject.setStatus(ComEnum.IsDelete.DEL.getValue());
        int success = baseMapper.updateById(consultingProject);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return  OutEnum.FAIL.getCode();
    }
}
