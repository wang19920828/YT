package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.common.comm.ProjectConstants;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsListOutputDTO;
import org.fh.general.ecom.common.dto.product.mongo.OutputMongoProjectDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOrderDetailDTO;
import org.fh.general.ecom.common.dto.product.organization.OutputOrganizationDetailDTO;
import org.fh.general.ecom.common.dto.product.project.*;
import org.fh.general.ecom.common.dto.product.projectdetail.*;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.dto.product.web.InputWebProjectDetailDTO;
import org.fh.general.ecom.common.dto.product.web.OutputWebProjectDetailAllDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.order.coupons.CouponsListOutPO;
import org.fh.general.ecom.common.po.product.project.*;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.client.BasicClient;
import org.fh.general.ecom.product.client.OrderClient;
import org.fh.general.ecom.product.dao.ProjectDao;
import org.fh.general.ecom.product.model.OrganizationInfo;
import org.fh.general.ecom.product.model.Project;
import org.fh.general.ecom.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 项目主信息 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Service
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {


    @Autowired
    private ProjectDetailService projectDetailService;
    @Autowired
    private ProjectFinancingService projectFinancingService;
    @Autowired
    private ProjectProgrammeService projectProgrammeService;
    @Autowired
    private ProjectFilesService projectFilesService;
    @Autowired
    private  ProjectTeamManageService projectTeamManageService;
    @Autowired
    private ProjectTeamBranchService projectTeamBranchService;
    @Autowired
    private OrganizationInfoService organizationInfoService;
    @Autowired
    private ProjectOperLogService projectOperLogService;
    @Autowired
    private ProjectMongoDBService projectMongoDBService;

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private BasicClient basicClient;


    @Override
    public OutPutProjectAddDTO addProjectMain(InputProjectAddMainDTO dto) {
        OutPutProjectAddDTO response = new OutPutProjectAddDTO();
        Project project = new Project();
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.where("project_status !={0}", ProjectEnum.ProjectStatus.CANCLE.getValue())
                .andNew("project_name={0} ", dto.getProjectName());
        int i = baseMapper.selectCount(wrapper);
        if (i > 0) {
            response.setCode(OutEnum.TIPS.getCode());
            return response;
        }

        BeanUtils.copyProperties(dto, project);
        if (StringUtils.isEmpty(dto.getProjectSource())) {
            project.setProjectSource(ProjectEnum.ProjectSource.MANAGE.getValue());
            project.setProjectStatus(ProjectEnum.ProjectStatus.APPLYING.getValue());
        } else {
            project.setProjectSource(ProjectEnum.ProjectSource.PC.getValue());
            project.setProjectStatus(ProjectEnum.ProjectStatus.CREATE.getValue());

        }
        project.setBranch(dto.getBranch() == null ? ComEnum.Branch.YUN_TOU.getValue() : dto.getBranch());
        project.setBranchName(dto.getBranchName() == null ? ComEnum.Branch.YUN_TOU.getName() : dto.getBranchName());
        project.setChannel(dto.getChannel() == null ? ComEnum.Channel.WEB.getValue() : dto.getChannel());
        project.setProjectNo(DateUtils.formatDate(new Date(), DateUtils.DATE_FROMAT4));
        project.setCreateDate(new Date());
        project.setUpdateBy(dto.getCreateBy());
        project.setUpdateDate(new Date());

        int success = baseMapper.insert(project);
        if (success > 0) {
            this.projectTeamBranchService.insertBatchList(dto, project.getId());
            this.projectTeamManageService.insertBatchList(dto, project.getId());
            response.setCode(OutEnum.SUCCESS.getCode());
            response.setId(project.getId());
            insertProjectOperLog(project,"");
            return response;
        }
        response.setCode(OutEnum.FAIL.getCode());
        return response;
    }

    private void insertProjectOperLog(Project project,String remarks) {
        InputProjectOperAddDTO inputProjectOperAddDTO=new InputProjectOperAddDTO();
        inputProjectOperAddDTO.setProjectId(project.getId());
        inputProjectOperAddDTO.setProjectStatus(project.getProjectStatus());
        inputProjectOperAddDTO.setOperId(project.getUpdateBy());
        inputProjectOperAddDTO.setRemarks(StringUtils.isEmpty(remarks)?ProjectEnum.ProjectStatus.codeOf(project.getProjectStatus()).getName():remarks);
        inputProjectOperAddDTO.setProjectSource(project.getProjectSource());
        OutputProjectFinancingDTO  outputProjectFinancingDTO = this.projectFinancingService.findDetailByProjectId(project.getId());
        if(outputProjectFinancingDTO!=null){
            inputProjectOperAddDTO.setAppointmentEndTime(outputProjectFinancingDTO.getEndTime());
            inputProjectOperAddDTO.setSubscribeForEndTime(outputProjectFinancingDTO.getPurchaseEndTime());
            inputProjectOperAddDTO.setSubscribeForSuccess(outputProjectFinancingDTO.getSuccessRate());
            inputProjectOperAddDTO.setDelayAfterTime(outputProjectFinancingDTO.getDelayDate());
            inputProjectOperAddDTO.setDelayBeforeTime(outputProjectFinancingDTO.getPurchaseEndTime());
            inputProjectOperAddDTO.setTotalAmount(outputProjectFinancingDTO.getTotalAmount());
            inputProjectOperAddDTO.setRedTerm(outputProjectFinancingDTO.getRedTerm());
        }

        if(ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue().equals(project.getProjectStatus()) || ProjectEnum.ProjectStatus.SUBSCRIBE.getValue().equals(project.getProjectStatus())) {

            OutputOrderDetailDTO detailDTO = orderClient.findAppointmentAmountByProjectId(project.getId() + "");
            if (detailDTO != null) {
                BigDecimal amount =   detailDTO.getAmount()==null?BigDecimal.ZERO:detailDTO.getAmount();
                BigDecimal   schedule = amount.divide(inputProjectOperAddDTO.getTotalAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                inputProjectOperAddDTO.setSubscribeForSchedule(schedule);
                inputProjectOperAddDTO.setSubscribeForStatus(ProjectEnum.SubscribeForStatus.START.getValue());
            }
        }else if(ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue().equals(project.getProjectStatus())){
            OutputOrderDetailDTO detailDTO = orderClient.findAppointmentAmountByProjectId(project.getId() + "");
            if (detailDTO != null) {
                BigDecimal amount =   detailDTO.getAmount()==null?BigDecimal.ZERO:detailDTO.getAmount();
                BigDecimal   schedule = amount.divide(inputProjectOperAddDTO.getTotalAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                inputProjectOperAddDTO.setAppointmentSchedule(schedule);
                inputProjectOperAddDTO.setAppointmentStatus(ProjectEnum.AppointmentStatus.START.getValue());
            }
        }
        projectOperLogService.insertEntity(inputProjectOperAddDTO);
    }

    @Override
    public OutputProjectListDTO findPage(InputProjectListDTO dto) {
        OutputProjectListDTO outputProjectListDTO = new OutputProjectListDTO();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        ProjectListOutputPO po = new ProjectListOutputPO();
        po.setProjectStatus(dto.getStatus()==null?ComEnum.IsDelete.NORMAL.getValue():dto.getStatus());
        po.setProjectName(dto.getProjectName());
        po.setProjectType(dto.getProjectType());
        po.setRightsType(dto.getRightsType());
        po.setBranch(dto.getBranch()==null? ComEnum.Branch.YUN_TOU.getValue():dto.getBranch());
        po.setBranchName(dto.getChannel()==null? ComEnum.Channel.WEB.getValue():dto.getChannel());
        List<ProjectListOutputPO> listpo = baseMapper.findData(po);

        Map<String,String> map = new HashMap<>();
        listpo.forEach((ProjectListOutputPO temp)->{
            temp.setProjectStatus(ProjectEnum.ProjectStatus.codeOf(temp.getProjectStatus()).getName());
            findDictionaryLabel(map, temp);
        });
        PageInfo pageInfo = new PageInfo(listpo);
        outputProjectListDTO.setList(listpo);
        outputProjectListDTO.setPageInfo(pageInfo);
        return outputProjectListDTO;
    }

    private void findDictionaryLabel(Map<String, String> map, ProjectListOutputPO temp) {
        InputDictionaryQueryDTO query = new InputDictionaryQueryDTO();
        if(!map.containsKey(ProjectConstants.DICTIONARY_PROJECT_TYPE+temp.getProjectType())){
            query.setType(ProjectConstants.DICTIONARY_PROJECT_TYPE);
            query.setValue(temp.getProjectType());
            OutputDictionaryDetailDTO outputDictionaryDetailDTO =  this.basicClient.findLabelByValueAndType(query);
            temp.setProjectTypeName(outputDictionaryDetailDTO==null?"":outputDictionaryDetailDTO.getLabel());
            map.put(ProjectConstants.DICTIONARY_PROJECT_TYPE+temp.getProjectType(),temp.getProjectTypeName());
        }else{
            temp.setProjectTypeName(map.get(ProjectConstants.DICTIONARY_PROJECT_TYPE+temp.getProjectType()));
        }
        if(!map.containsKey(ProjectConstants.DICTIONARY_RIGHTS_TYPE+temp.getRightsType())){
            query.setType(ProjectConstants.DICTIONARY_RIGHTS_TYPE);
            query.setValue(temp.getRightsType());
            OutputDictionaryDetailDTO outputDictionaryDetailDTO  =  this.basicClient.findLabelByValueAndType(query);
            temp.setRightsTypeName(outputDictionaryDetailDTO==null?"":outputDictionaryDetailDTO.getLabel());
            map.put(ProjectConstants.DICTIONARY_RIGHTS_TYPE+temp.getRightsType(),temp.getRightsTypeName());
        }else{
            temp.setRightsTypeName(map.get(ProjectConstants.DICTIONARY_RIGHTS_TYPE+temp.getRightsType()));
        }
    }

    @Override
    public OutPutProjectAddDTO addProjectDetail(InputProjectAddDetailDTO dto) {
        OutPutProjectAddDTO response = new OutPutProjectAddDTO();
        Project project = baseMapper.selectById(dto.getProjectId());
        if (project == null) {
            response.setCode(OutEnum.WARN.getCode());
            return response;
        }
        dto.setCreateBy(project.getCreateBy());
        if (StringUtils.isNotEmpty(dto.getProjectSummary())) {
            project.setProjectSummary(dto.getProjectSummary());
            project.setPcImageUrl(dto.getPcImageUrl());
            project.setAppImageUrl(dto.getAppImageUrl());
            project.setUpdateBy(dto.getUpdateBy());
            project.setUpdateDate(new Date());
            baseMapper.updateById(project);
        }
        if (this.projectDetailService.insertDetail(dto)) {
            response.setCode(OutEnum.SUCCESS.getCode());
            response.setId(project.getId());
            return response;
        }
        response.setCode(OutEnum.FAIL.getCode());
        response.setId(dto.getProjectId());
        return response;
    }

    @Override
    public OutPutProjectAddDTO addProjectFinancing(InputProjectAddFinancingDTO dto) {
        OutPutProjectAddDTO response = new OutPutProjectAddDTO();
        Project project = baseMapper.selectById(dto.getProjectId());
        if (project == null) {
            response.setCode(OutEnum.WARN.getCode());
            return response;
        }
        dto.setCreateBy(project.getCreateBy());
        dto.setBranch(project.getBranch());
        dto.setBranchName(project.getBranchName());
        if (this.projectFinancingService.insertFinancing(dto)) {
            response.setCode(OutEnum.SUCCESS.getCode());
            response.setId(dto.getProjectId());
            return response;
        }
        response.setCode(OutEnum.FAIL.getCode());
        response.setId(dto.getProjectId());
        return response;
    }

    @Override
    public OutPutProjectAddDTO addProjectProgrammes(InputProjectAddProgrammeListDTO dto) {
        OutPutProjectAddDTO response = new OutPutProjectAddDTO();
        Project project = baseMapper.selectById(dto.getProjectId());
        if (project == null) {
            response.setCode(OutEnum.WARN.getCode());
            return response;
        }
        dto.setCreateBy(project.getCreateBy());
        if (this.projectProgrammeService.insertBatchList(dto)) {
            response.setCode(OutEnum.SUCCESS.getCode());
            response.setId(dto.getProjectId());
            return response;
        }

        response.setCode(OutEnum.FAIL.getCode());
        response.setId(dto.getProjectId());
        return response;
    }

    @Override
    public OutPutProjectAddDTO addProjectFiles(InputProjectAddFilesListDTO dto) {
        OutPutProjectAddDTO response = new OutPutProjectAddDTO();
        Project project = baseMapper.selectById(dto.getProjectId());
        if(project==null){
            response.setCode(OutEnum.WARN.getCode());
            return response;
        }
        dto.setCreateBy(project.getCreateBy());
       if (this.projectFilesService.insertBatchList(dto)) {
            response.setCode(OutEnum.SUCCESS.getCode());
            response.setId(dto.getProjectId());
            return response;
        }
        response.setCode(OutEnum.FAIL.getCode());
        response.setId(dto.getProjectId());
        return response;
    }

    @Override
    public OutputProjectDetailDTO findDetail(InputProjectDetailDTO dto) {
        OutputProjectDetailDTO response = new OutputProjectDetailDTO();

        Project project = baseMapper.selectById(dto.getId());
        if (project != null) {
            //项目主信息
            ProjectMainDTO projectMainDTO = new ProjectMainDTO();
            BeanUtils.copyProperties(project, projectMainDTO);
            projectMainDTO.setProjectStatusDesc(ProjectEnum.ProjectStatus.codeOf(project.getProjectStatus()).getName());
            OutputOrganizationDetailDTO org= this.organizationInfoService.findEntity(project.getCompanyId());
            projectMainDTO.setCompanyName(org==null?"":org.getCompanyName());

            OutputDictionaryDetailDTO outputDictionaryDetailDTO = findDictionaryInfo(project.getProjectType(), ProjectConstants.DICTIONARY_PROJECT_TYPE);
            if(outputDictionaryDetailDTO!=null){
                projectMainDTO.setProjectTypeName(outputDictionaryDetailDTO.getLabel());
            }

            outputDictionaryDetailDTO = findDictionaryInfo(project.getRightsType(),ProjectConstants.DICTIONARY_RIGHTS_TYPE);
            if(outputDictionaryDetailDTO!=null){
                projectMainDTO.setRightsTypeName(outputDictionaryDetailDTO.getLabel());
            }

            outputDictionaryDetailDTO = findDictionaryInfo(project.getProjectSchedule(),ProjectConstants.DICTIONARY_PROJECT_SCHEDULE);
            if(outputDictionaryDetailDTO!=null){
                projectMainDTO.setProjectScheduleName(outputDictionaryDetailDTO.getLabel());
            }

            outputDictionaryDetailDTO = findDictionaryInfo(project.getProjectSchedule(),ProjectConstants.DICTIONARY_PROPERTY_WEIGHT);
            if(outputDictionaryDetailDTO!=null){
                projectMainDTO.setPropertyWeightName(outputDictionaryDetailDTO.getLabel());
            }
            String name = this.basicClient.findbySortCode(project.getAreaAddress());
            projectMainDTO.setAreaAddressName(name);

            response.setProjectMainDTO(projectMainDTO);

            //项目团队信息
            OutputProjectTeamManageListDTO outputProjectTeamManageListDTO = this.projectTeamManageService.findList(dto.getId());
            if(outputProjectTeamManageListDTO!=null) {
                List<ProjectTeamManageListOutputPO> list = outputProjectTeamManageListDTO.getProjectTeamManageListOutputPOList();
                if (list != null && list.size() > 0) {
                    List<ProjectTeamManageDTO> listvo = new ArrayList<ProjectTeamManageDTO>();
                    list.forEach((ProjectTeamManageListOutputPO temp) -> {
                        ProjectTeamManageDTO vo = new ProjectTeamManageDTO();
                        BeanUtils.copyProperties(temp, vo);
                        listvo.add(vo);
                    });
                    response.setProjectTeamManageDTOList(listvo);
                }
            }

            //平台团队
            OutputProjectTeamBranchListDTO outputProjectTeamBranchListDTO = this.projectTeamBranchService.findList(dto.getId());
            if(outputProjectTeamBranchListDTO!=null) {
                List<ProjectTeamBranchListOutputPO> branchListOutputPOList = outputProjectTeamBranchListDTO.getProjectTeamBranchListOutputPOList();
                if (branchListOutputPOList != null && branchListOutputPOList.size() > 0) {
                    List<ProjectTeamBranchDTO> listvo = new ArrayList<ProjectTeamBranchDTO>();
                    branchListOutputPOList.forEach((ProjectTeamBranchListOutputPO temp) -> {
                        ProjectTeamBranchDTO vo = new ProjectTeamBranchDTO();
                        BeanUtils.copyProperties(temp, vo);
                        listvo.add(vo);
                    });
                    response.setProjectTeamBranchDTOList(listvo);
                }
            }

            //项目详情
            OutputProjectDetailInfoDTO outputProjectDetailInfoDTO = this.projectDetailService.findDetailByProjectId(dto.getId());
            if (outputProjectDetailInfoDTO != null) {
                ProjectDetailInfoDTO projectDetailInfoDTO = new ProjectDetailInfoDTO();
                BeanUtils.copyProperties(outputProjectDetailInfoDTO, projectDetailInfoDTO);
                response.setProjectDetailInfoDTO(projectDetailInfoDTO);
            }

            //众筹信息
            OutputProjectFinancingDTO outputProjectFinancingDTO = this.projectFinancingService.findDetailByProjectId(dto.getId());
            if (outputProjectFinancingDTO != null) {
                ProjectFinancingDTO projectFinancingDTO = new ProjectFinancingDTO();
                BeanUtils.copyProperties(outputProjectFinancingDTO, projectFinancingDTO);
                response.setProjectFinancingDTO(projectFinancingDTO);
            }

            //方案信息
            OutputProjectProgrammeListDTO outputProjectProgrammeListDTO = this.projectProgrammeService.findList(dto.getId());
            if(outputProjectProgrammeListDTO!=null) {
                List<ProjectProgrammeListOutputPO> listOutputPOS = outputProjectProgrammeListDTO.getProjectProgrammeListOutputPOList();
                if (listOutputPOS != null && listOutputPOS.size() > 0) {
                    List<ProjectProgrammeDTO> listvo = new ArrayList<ProjectProgrammeDTO>();
                    listOutputPOS.forEach((ProjectProgrammeListOutputPO temp) -> {
                        ProjectProgrammeDTO vo = new ProjectProgrammeDTO();
                        BeanUtils.copyProperties(temp, vo);
                        vo.setCouponName(this.findCouponName(temp.getCouponId()));
                        vo.setUnitPrice(temp.getUnitPrice()+"");
                        vo.setExpectedReturnRate(temp.getExpectedReturnRate()+"");
                        listvo.add(vo);
                    });
                    response.setProjectProgrammeDTOList(listvo);
                }
            }

            //披露信息
            InputProjectFileListDTO inputProjectFileListDTO = new InputProjectFileListDTO();
            inputProjectFileListDTO.setProjectId(dto.getId());
            inputProjectFileListDTO.setOpen(ProjectEnum.ProjectFileOpen.OPEN.getValue());
            OutputProjectFilesListDTO outputProjectFilesListDTO = this.projectFilesService.findList(inputProjectFileListDTO);
            if(outputProjectFilesListDTO!=null) {
                List<ProjectFilesListOutputPO> openFiles = outputProjectFilesListDTO.getProjectFileListOutputPOList();
                if (openFiles != null && openFiles.size() > 0) {
                    List<ProjectFilesDTO> listvo = new ArrayList<ProjectFilesDTO>();
                    openFiles.forEach((ProjectFilesListOutputPO temp) -> {
                        ProjectFilesDTO vo = new ProjectFilesDTO();
                        BeanUtils.copyProperties(temp, vo);
                        listvo.add(vo);
                    });
                    response.setProjectFileDTOList(listvo);
                }
            }
            outputProjectFilesListDTO = this.projectFilesService.findList(inputProjectFileListDTO);
            if(outputProjectFilesListDTO!=null) {
                List<ProjectFilesListOutputPO> closedFiles = outputProjectFilesListDTO.getProjectFileListOutputPOList();
                if (closedFiles != null && closedFiles.size() > 0) {
                    List<ProjectFilesDTO> listvo = new ArrayList<ProjectFilesDTO>();
                    closedFiles.forEach((ProjectFilesListOutputPO temp) -> {
                        ProjectFilesDTO vo = new ProjectFilesDTO();
                        BeanUtils.copyProperties(temp, vo);
                        listvo.add(vo);
                    });
                    response.setPrivateFileDTOList(listvo);
                }
            }
            return response;
        }
        return null;
    }

    private String findCouponName(String couponIds){
        if(StringUtils.isNotEmpty(couponIds)){
           CouponsListOutputDTO couponsListOutputDTO = this.orderClient.findCouponByIds(couponIds);
           if(couponsListOutputDTO!=null && couponsListOutputDTO.getList()!=null && couponsListOutputDTO.getList().size()>0){
              StringBuffer couponsName = new StringBuffer();
               couponsListOutputDTO.getList().forEach((CouponsListOutPO temp) -> {
                   couponsName.append(temp.getCouponName()+",");
               });
              return couponsName.toString();
           }
        }
        return "";
    }
    private OutputDictionaryDetailDTO findDictionaryInfo(String value,String type) {
        InputDictionaryQueryDTO requestDto = new InputDictionaryQueryDTO();
        requestDto.setValue(value);
        requestDto.setType(type);
        return this.basicClient.findLabelByValueAndType(requestDto);
    }

    @Override
    public String updateDetail(InputProjectUpdateDTO dto) {
        Long  id = dto.getId();
        Project project =baseMapper.selectById(id);
        if(project ==null){
            return OutEnum.WARN.getMessage();
        }

        if(project!=null &&
                (!ProjectEnum.ProjectStatus.APPLYING.getValue().equals(project.getProjectStatus())
                        && !ProjectEnum.ProjectStatus.AUDIT_REJECT.getValue().equals(project.getProjectStatus())) )
        {
            return "只有【完善中】的项目才允许修改";
        }

        InputProjectAddMainDTO  inputProjectAddMainDTO = dto.getInputProjectAddMainDTO();
        if (inputProjectAddMainDTO!=null){
            InputProjectAddDetailDTO inputProjectAddDetailDTO= dto.getInputProjectAddDetailDTO();
            //修改主信息
            BeanUtils.copyProperties(inputProjectAddMainDTO,project);
            if(inputProjectAddDetailDTO!=null) {
                project.setPcImageUrl(inputProjectAddDetailDTO.getPcImageUrl());
                project.setAppImageUrl(inputProjectAddDetailDTO.getAppImageUrl());
                project.setProjectSummary(inputProjectAddDetailDTO.getProjectSummary());
            }
            project.setUpdateBy(dto.getUpdateBy());
            project.setUpdateDate(new Date());
            baseMapper.updateById(project);
            //修改详情
            if(inputProjectAddDetailDTO!=null){
                this.projectDetailService.insertDetail(inputProjectAddDetailDTO);
            }


            //项目管理团队
            String manageStr = inputProjectAddMainDTO.getManageTeamStr();
            if(StringUtils.isNotEmpty(manageStr)) {
                this.projectTeamManageService.insertBatchList(inputProjectAddMainDTO, id);
            }
            //平台管理团队
            String branchStr = inputProjectAddMainDTO.getBranchTeamStr();
            if(StringUtils.isNotEmpty(branchStr)) {
                this.projectTeamBranchService.insertBatchList(inputProjectAddMainDTO, id);
            }


            //筹资信息
            InputProjectAddFinancingDTO inputProjectAddFinancingDTO = dto.getInputProjectAddFinancingDTO();
            if(inputProjectAddFinancingDTO!=null){
                this.projectFinancingService.insertFinancing(inputProjectAddFinancingDTO);
            }

            //方案信息
            InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO = dto.getInputProjectAddProgrammeListDTO();
            if(inputProjectAddProgrammeListDTO!=null){
                this.projectProgrammeService.insertBatchList(inputProjectAddProgrammeListDTO);
            }

            //披露信息
            InputProjectAddFilesListDTO  inputProjectAddFilesListDTO = dto.getInputProjectAddFilesListDTO();
            if (inputProjectAddFilesListDTO!=null){
                this.projectFilesService.insertBatchList(inputProjectAddFilesListDTO);
            }
        }
        return  OutEnum.SUCCESS.getMessage();
    }

    @Override
    public  OutPutProjectAddDTO addAllInfo(InputProjectAllAddDTO dto) {
        OutPutProjectAddDTO outPutProjectAddDTO = new  OutPutProjectAddDTO();
        Project project = new Project();
        InputProjectAddMainDTO  inputProjectAddMainDTO = dto.getInputProjectAddMainDTO();
        if (inputProjectAddMainDTO!=null){
            //添加主信息  项目管理团队，平台团队
             InputProjectAddDetailDTO inputProjectAddDetailDTO = dto.getInputProjectAddDetailDTO();
             //补全摘要，pc 和app 宣传图片信息//
            if(inputProjectAddDetailDTO!=null) {
                if (StringUtils.isNotEmpty(inputProjectAddDetailDTO.getProjectSummary())) {
                    inputProjectAddMainDTO.setProjectSummary(inputProjectAddDetailDTO.getProjectSummary());
                }
                if(StringUtils.isNotEmpty(inputProjectAddDetailDTO.getPcImageUrl())){
                    inputProjectAddMainDTO.setPcImageUrl(inputProjectAddDetailDTO.getPcImageUrl());
                }
                if(StringUtils.isNotEmpty(inputProjectAddDetailDTO.getAppImageUrl())){
                    inputProjectAddDetailDTO.setAppImageUrl(inputProjectAddDetailDTO.getAppImageUrl());
                }

            }
            outPutProjectAddDTO =  addProjectMain(inputProjectAddMainDTO);
            if(outPutProjectAddDTO==null){
                return outPutProjectAddDTO;
            }
            Long projectId = outPutProjectAddDTO.getId();

            //新增详情详情
            inputProjectAddDetailDTO.setProjectId(projectId);
            if(inputProjectAddDetailDTO!=null){
                this.projectDetailService.insertDetail(inputProjectAddDetailDTO);
            }


            //筹资信息
            InputProjectAddFinancingDTO inputProjectAddFinancingDTO = dto.getInputProjectAddFinancingDTO();
            if(inputProjectAddFinancingDTO!=null){
                inputProjectAddFinancingDTO.setProjectId(projectId);
                inputProjectAddFinancingDTO.setUpdateBy(dto.getCreateBy());
                inputProjectAddFinancingDTO.setCreateBy(dto.getCreateBy());
                this.projectFinancingService.insertFinancing(inputProjectAddFinancingDTO);
            }

            //方案信息
            InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO = dto.getInputProjectAddProgrammeListDTO();
            if(inputProjectAddProgrammeListDTO!=null){
                inputProjectAddProgrammeListDTO.setProjectId(projectId);
                inputProjectAddProgrammeListDTO.setUpdateBy(dto.getCreateBy());
                inputProjectAddProgrammeListDTO.setCreateBy(dto.getCreateBy());
                this.projectProgrammeService.insertBatchList(inputProjectAddProgrammeListDTO);
            }

            //披露信息
            InputProjectAddFilesListDTO  inputProjectAddFilesListDTO = dto.getInputProjectAddFilesListDTO();
            if (inputProjectAddFilesListDTO!=null){
                inputProjectAddFilesListDTO.setProjectId(projectId);
                inputProjectAddFilesListDTO.setUpdateBy(dto.getCreateBy());
                inputProjectAddFilesListDTO.setCreateBy(dto.getCreateBy());
                this.projectFilesService.insertBatchList(inputProjectAddFilesListDTO);
            }
        }
        return outPutProjectAddDTO;
    }

    @Override
    public ProjectOneDetailDTO findDetailByProjectId(String projectId) {
        ProjectOneDetailDTO dto = new ProjectOneDetailDTO();
        Project entity=this.baseMapper.selectById(projectId);
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            OrganizationInfo companyEn = this.organizationInfoService.selectById(entity.getCompanyId());
            dto.setCompanyName(companyEn == null ? "某某公司" : companyEn.getCompanyName());
            //分红周期 tb_project_financing
            OutputProjectFinancingDTO financeEn = this.projectFinancingService.findDetailByProjectId(entity.getId());
            dto.setRedTerm(financeEn == null ? "12" : new String(financeEn.getRedTerm()));
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public OutputProjectMainInfoDTO findMainInfoByProjectId(String projectId) {
        OutputProjectMainInfoDTO mainInfoDTO= new OutputProjectMainInfoDTO();
        Project project = this.baseMapper.selectById(projectId);
        if(project!=null){
            BeanUtils.copyProperties(project,mainInfoDTO);
            return mainInfoDTO;
        }
        return null;
    }

    @Override
    public OutPutProjectAddDTO updateStatus(InputProjectUpdateStatusDTO dto) {
        OutPutProjectAddDTO  response = new OutPutProjectAddDTO();
        String ids = dto.getIds();
        if (StringUtils.isEmpty(ids)) {
            response.setCode(OutEnum.MUSTPARAMS.getCode());
            return response;
        }
        List<Long> list = StringUtils.strToLongList(ids);
        if (list == null || list.size() == 0) {
            response.setCode(OutEnum.MUSTPARAMS.getCode());
            return response;
        }
        List<ProjectListOutputPO> projects = new ArrayList<ProjectListOutputPO>();
        //完善资料
        if (ProjectEnum.ProjectStatus.APPLYING.getValue().equals(dto.getProjectStatus())) {
            projects  = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.CREATE.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //待发布
        else if (ProjectEnum.ProjectStatus.AUDITING.getValue().equals(dto.getProjectStatus())) {
            projects  = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.APPLYING.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //审核通过,更新状态，发布到前台
        else  if (ProjectEnum.ProjectStatus.AUDIT_PASS.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.AUDITING.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }

            insertMessageLog(projects,"上线了");


        }//审核驳回
        else if (ProjectEnum.ProjectStatus.AUDIT_REJECT.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.AUDITING.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //预约中
        else if (ProjectEnum.ProjectStatus.APPOINTMENT.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.AUDIT_PASS.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }

            insertMessageLog(projects,"开始预约了");
        }
        //预约认购中
        else if (ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.APPOINTMENT.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
            insertMessageLog(projects,"预约认购");

        }
        //公开认购中
        else if (ProjectEnum.ProjectStatus.SUBSCRIBE.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
            insertMessageLog(projects,"公开认购");
        }
        //冷静期
        else if (ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.SUBSCRIBE.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //分红中
        else if (ProjectEnum.ProjectStatus.IN_RED.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //认购失败
        else if (ProjectEnum.ProjectStatus.FAIL.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        }
        //延期 认购中并且未到 认购结实时间之前可以延期
        else if (ProjectEnum.ProjectStatus.DELAY.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.SUBSCRIBE.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误或者申购时间已经截至不允许延期");
                return response;
            }
        }
        //撤销--只有发布中，并且在预约开始时间之前可以撤销
        else if (ProjectEnum.ProjectStatus.CANCLE.getValue().equals(dto.getProjectStatus())) {
            projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.AUDIT_PASS.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误或者项目已经开始预约，不允许修改");
                return response;
            }
        }
        //认购成功
        else if (ProjectEnum.ProjectStatus.SUCCESS.getValue().equals(dto.getProjectStatus())) {
           projects = checkAndUpdateStatus(dto, list, ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue());
            if (projects == null) {
                response.setCode(OutEnum.TIPS.getCode());
                response.setMessage("项目状态有误");
                return response;
            }
        } else {
            response.setCode(OutEnum.MUSTPARAMS.getCode());
            response.setMessage(OutEnum.MUSTPARAMS.getMessage());
            return response;
        }

        //mongo操作
        updateMongoDB(dto, projects);

        if(projects!=null && projects.size()>0){
            projects.forEach((ProjectListOutputPO temp) -> {
                Project project = new Project();
                BeanUtils.copyProperties(temp, project);
                project.setProjectStatus(dto.getProjectStatus());
                if(dto.getProjectStatus().equals(ProjectEnum.ProjectStatus.SUCCESS.getValue())){
                    project.setSuccessDate(new Date());
                }
                insertProjectOperLog(project,dto.getRemarks());
            });
        }
        response.setCode(OutEnum.SUCCESS.getCode());
        return response;
    }

    private void insertMessageLog(List<ProjectListOutputPO> projects,String remarks) {
        projects.forEach((ProjectListOutputPO temp)->{
            UserMessageInsertInputDTO insertInputDTO = new UserMessageInsertInputDTO();
            insertInputDTO.setBranch(temp.getBranch());
            insertInputDTO.setImg(temp.getAppImageUrl());
            insertInputDTO.setMessage(temp.getProjectSummary());
            insertInputDTO.setTitle(temp.getProjectName()+remarks);
            insertInputDTO.setCorr(temp.getId()+"");
            insertInputDTO.setType("2");
            basicClient.insertUserMessage(insertInputDTO);
        });
    }

    //更新缓存操作
    private void updateMongoDB(InputProjectUpdateStatusDTO dto, List<ProjectListOutputPO> projects) {
        if(ProjectEnum.ProjectStatus.AUDIT_PASS.getValue().equals(dto.getProjectStatus())){
            Map<String,String> districtMap = new HashMap<String,String>();
            projects.forEach((ProjectListOutputPO temp)->{
                OutputMongoProjectDTO mongoProjectDTO= new OutputMongoProjectDTO();
                BeanUtils.copyProperties(temp,mongoProjectDTO);
                mongoProjectDTO.setId(temp.getId()+"");
                OutputProjectFinancingDTO projectFinancing = this.projectFinancingService.findDetailByProjectId(Long.valueOf(mongoProjectDTO.getId()));
                if(projectFinancing!=null){
                    BeanUtils.copyProperties(projectFinancing,mongoProjectDTO);
                }
                mongoProjectDTO.setProjectStatus(dto.getProjectStatus());
                mongoProjectDTO.setPublishDate(new Date());

                if(districtMap.containsKey(temp.getAreaAddress())){
                    String name =districtMap.get(temp.getAreaAddress());
                    mongoProjectDTO.setDistrict(name);
                }else{
                    String name = this.basicClient.findbySortCode(temp.getAreaAddress());
                    mongoProjectDTO.setDistrict(name);
                    districtMap.put(temp.getAreaAddress(),name);
                }
                OutputDictionaryDetailDTO outputDictionaryDetailDTO = findDictionaryInfo(temp.getProjectType(),ProjectConstants.DICTIONARY_PROJECT_TYPE);
                if(outputDictionaryDetailDTO!=null){
                    mongoProjectDTO.setIcon(outputDictionaryDetailDTO.getIcon());
                    mongoProjectDTO.setProjectTypeName(outputDictionaryDetailDTO.getLabel());
                }
                projectMongoDBService.insertOne(mongoProjectDTO);
            });
        }else if(ProjectEnum.ProjectStatus.CANCLE.getValue().equals(dto.getProjectStatus())){
            projects.forEach((ProjectListOutputPO temp)->{
                OutputMongoProjectDTO mongoProjectDTO= new OutputMongoProjectDTO();
                mongoProjectDTO.setId(temp.getId()+"");
                projectMongoDBService.delOne(mongoProjectDTO);
            });
        }else {
            projects.forEach((ProjectListOutputPO temp)->{
                OutputMongoProjectDTO mongoProjectDTO= new OutputMongoProjectDTO();
                BeanUtils.copyProperties(temp,mongoProjectDTO);
                mongoProjectDTO.setId(temp.getId()+"");
                OutputProjectFinancingDTO projectFinancing = this.projectFinancingService.findDetailByProjectId(Long.valueOf(mongoProjectDTO.getId()));
                if(projectFinancing!=null){
                    BeanUtils.copyProperties(projectFinancing,mongoProjectDTO);
                }
                mongoProjectDTO.setProjectStatus(dto.getProjectStatus());
                if(StringUtils.isNotEmpty(dto.getEndTime())) {
                    mongoProjectDTO.setPurchaseEndTime(DateUtils.getDate(dto.getEndTime()+" 23:59:59",DateUtils.DATE_FROMAT1));
                }
                projectMongoDBService.updateOne(mongoProjectDTO);
            });
        }
    }

    private List<ProjectListOutputPO> checkAndUpdateStatus(InputProjectUpdateStatusDTO dto, List<Long> list, String oldStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectStatus", oldStatus);
        map.put("list", list);
        List<ProjectListOutputPO> projects = projectDao.selectByStatus(map);
        if (projects == null ||  projects.size()== 0 || projects.size()!=list.size()) {
            return null;
        }
        if(ProjectEnum.ProjectStatus.DELAY.getValue().equals(dto.getProjectStatus())){
            Map<String, Object> delayMap = new HashMap<String, Object>();
            delayMap.put("isDelay",ProjectEnum.ProjectIsDelay.NORMAL.getValue());
            delayMap.put("list", list);
            delayMap.put("currentTime",new Date());
            List<ProjectFinancingOutputPO> financings = this.projectFinancingService.selectByParams(delayMap);
            if(financings==null ||  financings.size() !=list.size()){
                return null;
            }

            delayMap.put("isDelay",ProjectEnum.ProjectIsDelay.DELAY.getValue());
            delayMap.put("list", list);
            delayMap.put("delayDate",DateUtils.getDate(dto.getEndTime(),DateUtils.DATE_FROMAT1));
            this.projectFinancingService.updateList(delayMap);
        } else if(ProjectEnum.ProjectStatus.CANCLE.getValue().equals(dto.getProjectStatus())){
            Map<String, Object> cancleMap = new HashMap<String, Object>();
            cancleMap.put("list", list);
            cancleMap.put("cancleStartTime",new Date());
            List<ProjectFinancingOutputPO> financings = this.projectFinancingService.selectByParams(cancleMap);
            if(financings==null ||  financings.size() !=list.size()){
                return null;
            }
        }


            map.put("projectStatus", dto.getProjectStatus());
            map.put("updateBy",dto.getUpdateBy()==null?"1":dto.getUpdateBy());
            map.put("updateDate",new Date());
            if(ProjectEnum.ProjectStatus.AUDIT_PASS.getValue().equals(dto.getProjectStatus())){
                map.put("publishDate",new Date());
            }
            projectDao.updateBatchStatus(map);

        return projects;
    }




    public OutputWebProjectDetailAllDTO findWebDetail(InputWebProjectDetailDTO dto){
        OutputWebProjectDetailAllDTO response= new OutputWebProjectDetailAllDTO();
        Project  project  = baseMapper.selectById(dto.getId());
        if(project==null){
            return null;
        }

        BeanUtils.copyProperties(project,response);

        OutputDictionaryDetailDTO outputDictionaryDetailDTO = findDictionaryInfo(project.getProjectType(),ProjectConstants.DICTIONARY_PROJECT_TYPE);

        if(outputDictionaryDetailDTO!=null){
            response.setIcon(outputDictionaryDetailDTO.getIcon());
            response.setProjectTypeName(outputDictionaryDetailDTO.getLabel());
        }

        if(StringUtils.isNotEmpty(project.getCompanyId()+"")) {
            OutputOrganizationDetailDTO outputOrganizationDetailDTO = this.organizationInfoService.findEntity(project.getCompanyId());
            if(outputOrganizationDetailDTO!=null){
                response.setCompanyName(outputOrganizationDetailDTO.getCompanyName());
                response.setLogo(outputOrganizationDetailDTO.getLogo());
            }
        }

        String name = this.basicClient.findbySortCode(response.getAreaAddress());
        response.setDistrict(name);

        OutputProjectDetailInfoDTO projectDetail = this.projectDetailService.findDetailByProjectId(dto.getId());
        if(projectDetail!=null){
            response.setPcHeaderImages(projectDetail.getPcHeaderImages());
            response.setAppHeaderImages(projectDetail.getAppHeaderImages());
            response.setProjectDetail(projectDetail.getProjectDetail());
            response.setProgrammeDetail(projectDetail.getProgrammeDetail());
        }

        OutputProjectFinancingDTO projectFinancing = this.projectFinancingService.findDetailByProjectId(dto.getId());
        if(projectFinancing!=null){
            response.setTotalAmount(projectFinancing.getTotalAmount());
            try {
                if (ProjectEnum.ProjectStatus.APPOINTMENT.getValue().equals(project.getProjectStatus())){
                    response.setRemainingTime(DateUtils.getBetweenTime(new Date(), projectFinancing.getEndTime()));
                }else if (ProjectEnum.ProjectStatus.SUBSCRIBE.getValue().equals(project.getProjectStatus())){
                    response.setRemainingTime(DateUtils.getBetweenTime(new Date(), projectFinancing.getPurchaseEndTime()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ProjectEnum.ProjectStatus.APPOINTMENT.getValue().equals(project.getProjectStatus())){
            //查询预约金额
            OutputOrderDetailDTO out =  orderClient.findAppointmentAmountByProjectId(project.getId()+"");
            setAmountAndSchedule(response, out);

        }else if (ProjectEnum.ProjectStatus.SUBSCRIBE.getValue().equals(project.getProjectStatus())){
            //查询认购金额
            OutputOrderDetailDTO out =  orderClient.findBySubscribeForAmountProjectId(project.getId()+"");
            setAmountAndSchedule(response, out);
        }
        InputProjectFileListDTO  req= new InputProjectFileListDTO();
        req.setProjectId(dto.getId());
        req.setOpen(ProjectEnum.ProjectFileOpen.OPEN.getValue());
        OutputProjectFilesListDTO resDto  = this.projectFilesService.findList(req);
        if(resDto!=null){
            List<ProjectFilesListOutputPO>  listPO =  resDto.getProjectFileListOutputPOList();
            response.setOpenList(listPO);
        }

        req.setOpen(ProjectEnum.ProjectFileOpen.CLOSED.getValue());
        resDto  = this.projectFilesService.findList(req);
        if(resDto!=null){
            List<ProjectFilesListOutputPO>  listPO =  resDto.getProjectFileListOutputPOList();
            response.setPrivateList(listPO);
        }
        return response;
    }

    private void setAmountAndSchedule(OutputWebProjectDetailAllDTO response, OutputOrderDetailDTO out) {
        if(out!=null){
            response.setAmount(out.getAmount()==null? BigDecimal.ZERO:out.getAmount());
            response.setSchedule(response.getAmount().divide(response.getTotalAmount()).setScale(2, BigDecimal.ROUND_UP)+"");
        }else{
            response.setAmount(BigDecimal.ZERO);
            response.setSchedule("0.00");
        }
    }

    @Override
    public BigDecimal findFinishedAmount(String projectStatus, String projectId) {
            if(ProjectEnum.ProjectStatus.APPOINTMENT.getValue().equals(projectStatus)){
                //查询预约金额
                OutputOrderDetailDTO out =  orderClient.findAppointmentAmountByProjectId(projectId);
                if(out!=null){
                    return out.getAmount()==null?BigDecimal.ZERO:out.getAmount();
                }
            }else if (ProjectEnum.ProjectStatus.SUBSCRIBE.getValue().equals(projectStatus)) {
                //查询认购金额
                OutputOrderDetailDTO out = orderClient.findBySubscribeForAmountProjectId(projectId);
                if (out != null) {
                    return out.getAmount()==null?BigDecimal.ZERO:out.getAmount();
                }
            }else{
                OutputOrderDetailDTO out = orderClient.findBySubscribeForAmountProjectId(projectId);
                if (out != null) {
                    return out.getAmount()==null?BigDecimal.ZERO:out.getAmount();
                }
            }
        return BigDecimal.ZERO;
    }


    public void taskRun(){
        //修改为预约中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("projectStatus",ProjectEnum.ProjectStatus.AUDIT_PASS.getValue());
        map.put("yuyueStartTime",new Date());
        List<String> list =  this.baseMapper.findListByParams(map);

        String ids = getIdString(list);
        if(StringUtils.isNotEmpty(ids)){
            InputProjectUpdateStatusDTO dto = new InputProjectUpdateStatusDTO();
            dto.setIds(ids);
            dto.setProjectStatus(ProjectEnum.ProjectStatus.APPOINTMENT.getValue());
            updateStatus(dto);
        }


        //修改为预约认购中
        map = new HashMap<String,Object>();
        map.put("projectStatus",ProjectEnum.ProjectStatus.APPOINTMENT.getValue());
        map.put("yuyueRenGouTime",new Date()); //当前时间
        list =  this.baseMapper.findListByParams(map);

        ids = getIdString(list);
        if(StringUtils.isNotEmpty(ids)){
            InputProjectUpdateStatusDTO dto = new InputProjectUpdateStatusDTO();
            dto.setIds(ids);
            dto.setProjectStatus(ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue());
            updateStatus(dto);
        }
        //修改为认购中
        map = new HashMap<String,Object>();
        map.put("projectStatus",ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue());
        map.put("rengouStartTime",new Date());
        list =  this.baseMapper.findListByParams(map);
        ids = getIdString(list);
        if(StringUtils.isNotEmpty(ids)){
            InputProjectUpdateStatusDTO dto = new InputProjectUpdateStatusDTO();
            dto.setIds(ids);
            dto.setProjectStatus(ProjectEnum.ProjectStatus.SUBSCRIBE.getValue());
            updateStatus(dto);
        }

        //将认购结束后/延期中 修改为冷静期
        map = new HashMap<String,Object>();
        map.put("projectStatus",ProjectEnum.ProjectStatus.SUBSCRIBE.getValue());
        map.put("rengouEndTime",new Date());
        list =  this.baseMapper.findListByParams(map);
        ids = getIdString(list);
        if(StringUtils.isNotEmpty(ids)){
            updateCalmnessPeriod(ids) ;
        }
    }

    private String getIdString(List<String> list) {
        String ids = "";
        if(list!=null && list.size()>0){
            for(String id :list){
                System.out.println(id);
                ids+=id+",";
            }
        }
        return ids;
    }

    //修改为冷静期
    public void updateCalmnessPeriod(String ids){
        InputProjectUpdateStatusDTO dto = new InputProjectUpdateStatusDTO();
        dto.setIds(ids);
        dto.setProjectStatus(ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue());
        dto.setUpdateBy("1");
        updateStatus(dto);
    }
}
