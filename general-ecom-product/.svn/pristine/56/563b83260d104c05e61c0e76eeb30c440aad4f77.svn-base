package org.fh.general.ecom.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.product.project.*;
import org.fh.general.ecom.common.dto.product.projectdetail.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.product.project.ProjectListOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.product.project.*;
import org.fh.general.ecom.product.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目主信息 前端控制器
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("P000001")
    public PagingVO findPage(InputProjectListDTO dto){
        PagingVO pageVO = new PagingVO();
        OutputProjectListDTO response =projectService.findPage(dto);
        List<ProjectListVO> listvo= new ArrayList<ProjectListVO>();
        List<ProjectListOutputPO>  list = response.getList();
        if(list!=null && list.size()>0) {
            list.forEach((ProjectListOutputPO temp) -> {
                ProjectListVO vo = new ProjectListVO();
                BeanUtils.copyProperties(temp, vo);
                listvo.add(vo);
            });
            pageVO.success(listvo, response.getPageInfo());
        }else{
            pageVO.noData();
        }
        return pageVO;
    }

    @RequestMapping("P000002")
    public BaseVO addProjectMain(InputProjectAddMainDTO dto){
        BaseVO baseVo = new BaseVO();

        createTeamList(dto);
        OutPutProjectAddDTO response  = projectService.addProjectMain(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo, response.getCode());
            return  baseVo;
        }
        baseVo.setData(response);
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("P000003")
    public BaseVO addProjectDetail(InputProjectAddDetailDTO dto){
        BaseVO baseVo = new BaseVO();
        OutPutProjectAddDTO response  = projectService.addProjectDetail(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo, response.getCode());
            return  baseVo;
        }
        baseVo.setData(response);
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("P000004")
    public BaseVO addProjectFinancing(InputProjectAddFinancingDTO dto){
        BaseVO baseVo = new BaseVO();
        OutPutProjectAddDTO response  = projectService.addProjectFinancing(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo, response.getCode());
            return  baseVo;
        }
        baseVo.setData(response);
        baseVo.success();
        return baseVo;
    }

    @RequestMapping("P000005")
    public BaseVO addProjectProgrammes(InputProjectAddProgrammeListDTO dto){
        BaseVO baseVo = new BaseVO();

        String str = dto.getProgrammeStr();
        if(StringUtils.isNotEmpty(str)){
            List<InputProjectAddProgrammeDTO> listDto = JSON.parseArray(str, InputProjectAddProgrammeDTO.class);
            dto.setList(listDto);
        }
        OutPutProjectAddDTO response  = projectService.addProjectProgrammes(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo, response.getCode());
            return  baseVo;
        }
        baseVo.setData(response);
        baseVo.success();
        return baseVo;
    }
    @RequestMapping("P000006")
    public BaseVO addProjectFiles(InputProjectAddFilesListDTO dto){
        BaseVO baseVo = new BaseVO();
        String str = dto.getPublicInfos();
        if(StringUtils.isNotEmpty(str)){
            List<InputProjectAddFileDTO> listDto = JSON.parseArray(str, InputProjectAddFileDTO.class);
            dto.setPublicList(listDto);
        }
        str =dto.getPrivateInfos();
        if(StringUtils.isNotEmpty(str)){
            List<InputProjectAddFileDTO> listDto = JSON.parseArray(str, InputProjectAddFileDTO.class);
            dto.setPrivateList(listDto);
        }
        OutPutProjectAddDTO response  = projectService.addProjectFiles(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo, response.getCode());
            return  baseVo;
        }
        baseVo.setData(response);
        baseVo.success();
        return baseVo;
    }


    private void  getTipMsg(BaseVO baseVo, String code) {
        if(OutEnum.TIPS.getCode().equals(code)){
            baseVo.setBusAlert("项目名称不得重复");
        }else if(OutEnum.WARN.getCode().equals(code)) {
            baseVo.error(OutEnum.WARN.getMessage());
        }else{
            baseVo.error(OutEnum.FAIL.getMessage());
        }
    }

    @RequestMapping("P000007")
    public BaseVO findDetail(InputProjectDetailDTO dto){
        BaseVO baseVo = new BaseVO();
        OutputProjectDetailDTO response = this.projectService.findDetail(dto);
        if(response==null){
            baseVo.error(OutEnum.WARN.getMessage());
            return baseVo;
        }
        ProjectDetailVO vo = new ProjectDetailVO();

        ProjectMainVO projectMainVO=new ProjectMainVO();
        ProjectMainDTO projectMainDTO= response.getProjectMainDTO();
        if(projectMainDTO!=null) {
            BeanUtils.copyProperties(projectMainDTO, projectMainVO);
            vo.setProjectMainVo(projectMainVO);
        }


        List<ProjectTeamManageDTO>  teamManageDTOList  = response.getProjectTeamManageDTOList();
        if(teamManageDTOList!=null && teamManageDTOList.size()>0){
            List<ProjectTeamManageVO> teamManageVOList = new ArrayList<ProjectTeamManageVO>();
            teamManageDTOList.forEach((ProjectTeamManageDTO temp) -> {
                ProjectTeamManageVO projectTeamManageVO = new ProjectTeamManageVO();
                BeanUtils.copyProperties(temp, projectTeamManageVO);
                teamManageVOList.add(projectTeamManageVO);
            });
            vo.setProjectTeamManageVOList(teamManageVOList);
        }

        List<ProjectTeamBranchDTO> branchDTOList= response.getProjectTeamBranchDTOList();

        if(branchDTOList!=null && branchDTOList.size()>0){
            List<ProjectTeamBranchVO> teamBranchVOList = new ArrayList<ProjectTeamBranchVO>();
            branchDTOList.forEach((ProjectTeamBranchDTO temp) -> {
                ProjectTeamBranchVO projectTeamManageVO = new ProjectTeamBranchVO();
                BeanUtils.copyProperties(temp, projectTeamManageVO);
                teamBranchVOList.add(projectTeamManageVO);
            });
            vo.setProjectTeamBranchVOList(teamBranchVOList);
        }

        ProjectDetailInfoVO projectDetailInfoVO=new ProjectDetailInfoVO();
        ProjectDetailInfoDTO projectDetailInfoDTO = response.getProjectDetailInfoDTO();
        if(projectDetailInfoDTO!=null) {
            BeanUtils.copyProperties(projectDetailInfoDTO, projectDetailInfoVO);
            vo.setProjectDetailInfoVO(projectDetailInfoVO);
        }

        ProjectFinancingVO projectFinancingVO=new ProjectFinancingVO();
        ProjectFinancingDTO projectFinancingDTO = response.getProjectFinancingDTO();
        if(projectFinancingDTO!=null) {
            BeanUtils.copyProperties(projectFinancingDTO, projectFinancingVO);
            vo.setProjectFinancingVO(projectFinancingVO);
        }

        List<ProjectProgrammeDTO> projectProgrammeDTOList =  response.getProjectProgrammeDTOList();
        if(projectProgrammeDTOList!=null && projectProgrammeDTOList.size()>0){
            List<ProjectProgrammeVO> projectProgrammeVOS = new ArrayList<ProjectProgrammeVO>();
            projectProgrammeDTOList.forEach((ProjectProgrammeDTO temp) -> {
                ProjectProgrammeVO projectProgrammeVO = new ProjectProgrammeVO();
                BeanUtils.copyProperties(temp, projectProgrammeVO);
                projectProgrammeVOS.add(projectProgrammeVO);
            });
            vo.setProjectProgrammeVOList(projectProgrammeVOS);
        }

        List<ProjectFilesDTO> projectFiles   = response.getProjectFileDTOList();
        if(projectFiles!=null && projectFiles.size()>0){
            List<ProjectFilesVO> projectFilesVOS = new ArrayList<ProjectFilesVO>();
            projectFiles.forEach((ProjectFilesDTO temp) -> {
                ProjectFilesVO projectFilesVO = new ProjectFilesVO();
                BeanUtils.copyProperties(temp, projectFilesVO);
                projectFilesVOS.add(projectFilesVO);
            });
            vo.setProjectFileVOList(projectFilesVOS);
        }


        List<ProjectFilesDTO>  filesDTOS   = response.getPrivateFileDTOList();
        if(filesDTOS!=null && filesDTOS.size()>0){
            List<ProjectFilesVO> projectFilesVOS = new ArrayList<ProjectFilesVO>();
            filesDTOS.forEach((ProjectFilesDTO temp) -> {
                ProjectFilesVO projectFilesVO = new ProjectFilesVO();
                BeanUtils.copyProperties(temp, projectFilesVO);
                projectFilesVOS.add(projectFilesVO);
            });
            vo.setPrivateFileVOList(projectFilesVOS);
        }
        baseVo.success(vo);
        return baseVo;
    }


    @RequestMapping("P000008")
    public BaseVO updateEntity(InputProjectUpdateDTO dto){
        BaseVO baseVo = new BaseVO();

        String mainStr = dto.getProjectAddMainStr();
        InputProjectAddMainDTO inputProjectAddMainDTO = JSONObject.parseObject(mainStr,InputProjectAddMainDTO.class);
        dto.setInputProjectAddMainDTO(JSONObject.parseObject(mainStr,InputProjectAddMainDTO.class));
        createTeamList(inputProjectAddMainDTO);

        String detailStr = dto.getProjectAddDetailStr();
        InputProjectAddDetailDTO inputProjectAddDetailDTO =JSONObject.parseObject(detailStr,InputProjectAddDetailDTO.class);
        inputProjectAddDetailDTO.setUpdateBy(dto.getUpdateBy());
        dto.setInputProjectAddDetailDTO(inputProjectAddDetailDTO);

        String  projectFinancingStr = dto.getProjectFinancingStr();
        InputProjectAddFinancingDTO inputProjectAddFinancingDTO = JSONObject.parseObject(projectFinancingStr,InputProjectAddFinancingDTO.class);
        inputProjectAddFinancingDTO.setUpdateBy(dto.getUpdateBy());
        dto.setInputProjectAddFinancingDTO(inputProjectAddFinancingDTO);

        String str = dto.getProjectProgrammeStr();
        InputProjectAddProgrammeListDTO  inputProjectAddProgrammeListDTO = JSONObject.parseObject(str,InputProjectAddProgrammeListDTO.class);
        inputProjectAddProgrammeListDTO.setUpdateBy(dto.getUpdateBy());
        dto.setInputProjectAddProgrammeListDTO(inputProjectAddProgrammeListDTO);
        createProgrammeList(inputProjectAddProgrammeListDTO);


        String fileStr = dto.getProjectFilesStr();
        InputProjectAddFilesListDTO  inputProjectFileListDTO = JSONObject.parseObject(fileStr,InputProjectAddFilesListDTO.class);
        inputProjectFileListDTO.setUpdateBy(dto.getUpdateBy());
        dto.setInputProjectAddFilesListDTO(inputProjectFileListDTO);
        createFilesList(inputProjectFileListDTO);

        String code = this.projectService.updateDetail(dto);
        if(!OutEnum.SUCCESS.getMessage().equals(code)){
            baseVo.setBusAlert(code);
            return baseVo;
        }
        baseVo.success();
        baseVo.setData(dto.getId());
        return baseVo;
    }


    @RequestMapping("P000009")
    public BaseVO addAllEntity(InputProjectAllAddDTO  dto){
        BaseVO baseVo = new BaseVO();

        if(StringUtils.isNotEmpty(dto.getInputProjectAddMainStr())) {
            InputProjectAddMainDTO inputProjectAddMainDTO = JSONObject.parseObject(dto.getInputProjectAddMainStr(), InputProjectAddMainDTO.class);
            inputProjectAddMainDTO.setCreateBy(dto.getCreateBy());
            dto.setInputProjectAddMainDTO(inputProjectAddMainDTO);
            createTeamList(inputProjectAddMainDTO);
        }


        if(StringUtils.isNotEmpty(dto.getInputProjectAddProgrammeListStr())) {
            InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO = JSONObject.parseObject(dto.getInputProjectAddProgrammeListStr(), InputProjectAddProgrammeListDTO.class);
            inputProjectAddProgrammeListDTO.setCreateBy(dto.getCreateBy());
            dto.setInputProjectAddProgrammeListDTO(inputProjectAddProgrammeListDTO);
            createProgrammeList(inputProjectAddProgrammeListDTO);
        }

        if(StringUtils.isNotEmpty(dto.getInputProjectAddFilesListStr())) {
            InputProjectAddFilesListDTO inputProjectFileListDTO = JSONObject.parseObject(dto.getInputProjectAddFilesListStr(), InputProjectAddFilesListDTO.class);
            inputProjectFileListDTO.setCreateBy(dto.getCreateBy());
            dto.setInputProjectAddFilesListDTO(inputProjectFileListDTO);
            createFilesList(inputProjectFileListDTO);
        }

        if(StringUtils.isNotEmpty(dto.getInputProjectAddDetailStr())) {
            InputProjectAddDetailDTO inputProjectAddDetailDTO = JSONObject.parseObject(dto.getInputProjectAddDetailStr(), InputProjectAddDetailDTO.class);
            inputProjectAddDetailDTO.setCreateBy(dto.getCreateBy());
            dto.setInputProjectAddDetailDTO(inputProjectAddDetailDTO);
        }

        if(StringUtils.isNotEmpty(dto.getInputProjectAddFinancingStr())) {
            InputProjectAddFinancingDTO inputProjectAddFinancing = JSONObject.parseObject(dto.getInputProjectAddFinancingStr(), InputProjectAddFinancingDTO.class);
            inputProjectAddFinancing.setCreateBy(dto.getCreateBy());
            dto.setInputProjectAddFinancingDTO(inputProjectAddFinancing);
        }


        OutPutProjectAddDTO response = this.projectService.addAllInfo(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            getTipMsg(baseVo,response.getCode());
            return baseVo;
        }
        baseVo.success();
        baseVo.setData(response.getId());
        return baseVo;
    }

    private void createFilesList(InputProjectAddFilesListDTO inputProjectFileListDTO) {
        if(inputProjectFileListDTO!=null) {
            String str = inputProjectFileListDTO.getPublicInfos();
            if (StringUtils.isNotEmpty(str)) {
                List<InputProjectAddFileDTO> listDto = JSON.parseArray(str, InputProjectAddFileDTO.class);
                inputProjectFileListDTO.setPublicList(listDto);
            }
            str = inputProjectFileListDTO.getPrivateInfos();
            if (StringUtils.isNotEmpty(str)) {
                List<InputProjectAddFileDTO> listDto = JSON.parseArray(str, InputProjectAddFileDTO.class);
                inputProjectFileListDTO.setPrivateList(listDto);
            }
        }
    }

    private void createProgrammeList(InputProjectAddProgrammeListDTO inputProjectAddProgrammeListDTO) {
        if(inputProjectAddProgrammeListDTO!=null) {
            String  str = inputProjectAddProgrammeListDTO.getProgrammeStr();
            if (StringUtils.isNotEmpty(str)) {
                List<InputProjectAddProgrammeDTO> listDto = JSON.parseArray(str, InputProjectAddProgrammeDTO.class);
                inputProjectAddProgrammeListDTO.setList(listDto);
            }
        }
    }

    private void createTeamList(InputProjectAddMainDTO inputProjectAddMainDTO) {
        if(inputProjectAddMainDTO!=null) {
            String str = inputProjectAddMainDTO.getManageTeamStr();
            createTeamManage(inputProjectAddMainDTO, str);
            str = inputProjectAddMainDTO.getBranchTeamStr();
            createTeamBranch(inputProjectAddMainDTO, str);
        }
    }

    private void createTeamBranch(InputProjectAddMainDTO inputProjectAddMainDTO, String str) {
        if (StringUtils.isNotEmpty(str)) {
            List<InputProjectADDBranchDTO> listDto = JSON.parseArray(str, InputProjectADDBranchDTO.class);
            inputProjectAddMainDTO.setBranchDTOList(listDto);
        }
    }

    private void createTeamManage(InputProjectAddMainDTO inputProjectAddMainDTO, String str) {
        if (StringUtils.isNotEmpty(str)) {
            List<InputProjectADDManageDTO> listDto = JSON.parseArray(str, InputProjectADDManageDTO.class);
            inputProjectAddMainDTO.setManageDTOList(listDto);
        }
    }



    @RequestMapping("P000010")
    public BaseVO updateProjectStatus(InputProjectUpdateStatusDTO dto){
        return updateStatusByProjectIds(dto);
    }


    @RequestMapping("RP000010")
    public BaseVO updateStatus(@RequestBody InputProjectUpdateStatusDTO dto){
        return updateStatusByProjectIds(dto);
    }

    private BaseVO updateStatusByProjectIds(InputProjectUpdateStatusDTO dto) {
        BaseVO baseVo = new BaseVO();
        OutPutProjectAddDTO response  = this.projectService.updateStatus(dto);
        if(!OutEnum.SUCCESS.getCode().equals(response.getCode())){
            if(StringUtils.isNotEmpty(response.getMessage())) {
                baseVo.setBusAlert(response.getMessage());
            }else{
                baseVo.error("修改失败");
            }
            return baseVo;
        }
        baseVo.success();
        return baseVo;
    }


    @RequestMapping("RP000011")
    public ProjectOneDetailVO findDetailByProjectId(@RequestBody String projectId){
        ProjectOneDetailDTO response = this.projectService.findDetailByProjectId(projectId);
        if(response !=null){
            ProjectOneDetailVO vo = new ProjectOneDetailVO();
            BeanUtils.copyProperties(response,vo );
            return vo;
        }
        return null;
    }



}
