package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.common.dto.product.appraise.*;
import org.fh.general.ecom.common.dto.product.project.OutputProjectMainInfoDTO;
import org.fh.general.ecom.common.dto.product.user.OutputUserDetailDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.product.appraise.InputProjectAppraisePO;
import org.fh.general.ecom.common.po.product.appraise.OutputProjectAppraiseDetailPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.client.BasicClient;
import org.fh.general.ecom.product.dao.ProjectAppraiseDao;
import org.fh.general.ecom.product.model.ProjectAppraise;
import org.fh.general.ecom.product.service.ProjectAppraiseService;
import org.fh.general.ecom.product.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 项目评价信息 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-21
 */
@Service
public class ProjectAppraiseServiceImpl extends ServiceImpl<ProjectAppraiseDao, ProjectAppraise> implements ProjectAppraiseService {

    @Autowired
    private ProjectAppraiseDao projectAppraiseDao;
    @Autowired
    private BasicClient userClient;

    @Autowired
    private ProjectService projectService;
    @Override
    public OutputProjectAppraiseListDTO findPage(InputProjectAppraiseListDTO dto) {
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageSize() );
        OutputProjectAppraiseListDTO outputProjectAppraiseListDTO = new OutputProjectAppraiseListDTO();
        InputProjectAppraisePO  inputProjectAppraisePO = new InputProjectAppraisePO();
        BeanUtils.copyProperties(dto,inputProjectAppraisePO);
        if(StringUtils.isNotEmpty(dto.getStartTimeStr()) ) {
            inputProjectAppraisePO.setStartTimeStr(DateUtils.getDate(dto.getStartTimeStr() + " 00:00:00", DateUtils.DATE_FROMAT2));
        }
        if(StringUtils.isNotEmpty(dto.getEndTimeStr()) ) {
            inputProjectAppraisePO.setEndTimeStr(DateUtils.getDate(dto.getEndTimeStr() + " 23:59:59", DateUtils.DATE_FROMAT2));
        }
        List<OutputProjectAppraiseDetailPO> list=projectAppraiseDao.findList(inputProjectAppraisePO);
        PageInfo pageInfo=new PageInfo(list);
        List<OutputProjectAppriaiseDetailDTO>  listpo=new ArrayList<OutputProjectAppriaiseDetailDTO>();
        Map<String,Object> map = new HashMap<String,Object>();
        list.forEach((OutputProjectAppraiseDetailPO temp) -> {
            OutputProjectAppriaiseDetailDTO outputProjectAppriaiseDetailDTO=new OutputProjectAppriaiseDetailDTO();
            BeanUtils.copyProperties(temp,outputProjectAppriaiseDetailDTO);
            if(StringUtils.isNotEmpty(dto.getAppraiseType()) && ProjectEnum.ProjectAppraiseType.APPRAISE_TYPE.getValue().equals(temp.getAppraiseType())){
                inputProjectAppraisePO.setAppraiseId(temp.getId()+"");
                inputProjectAppraisePO.setAppraiseType(ProjectEnum.ProjectAppraiseType.REPLAY_APPRISE.getValue());
                List<OutputProjectAppraiseDetailPO> replyList=projectAppraiseDao.findList(inputProjectAppraisePO);
                if(replyList!=null && replyList.size()>0){
                    List<OutputProjectAppriaiseDetailDTO> replyDtoList = new ArrayList<OutputProjectAppriaiseDetailDTO>();
                    replyList.forEach((OutputProjectAppraiseDetailPO outputProjectAppraiseDetailPO) -> {
                        OutputProjectAppriaiseDetailDTO reply=new OutputProjectAppriaiseDetailDTO();
                        BeanUtils.copyProperties(outputProjectAppraiseDetailPO,reply);
                        replyDtoList.add(reply);
                    });
                    outputProjectAppriaiseDetailDTO.setReplyList(replyDtoList);
                }
            }
            findUserInfo(map, outputProjectAppriaiseDetailDTO);
            listpo.add(outputProjectAppriaiseDetailDTO);
        });
        outputProjectAppraiseListDTO.setList(listpo);
        outputProjectAppraiseListDTO.setPageInfo(pageInfo);
        return outputProjectAppraiseListDTO;
    }

    private void findUserInfo(Map<String, Object> map, OutputProjectAppriaiseDetailDTO outputProjectAppriaiseDetailDTO) {
        OutputUserDetailDTO user;
        if(map.containsKey(outputProjectAppriaiseDetailDTO.getCreateBy())){
            user =(OutputUserDetailDTO) map.get(outputProjectAppriaiseDetailDTO.getCreateBy());
        }else{
            user = userClient.findUserByUserId(Long.valueOf(outputProjectAppriaiseDetailDTO.getCreateBy()));
            if(user!=null ) {
                map.put(outputProjectAppriaiseDetailDTO.getCreateBy(), user);
            }
        }
        if(user!=null){
            outputProjectAppriaiseDetailDTO.setNickName(user.getNickName());
            outputProjectAppriaiseDetailDTO.setPhone(user.getPhone());
            outputProjectAppriaiseDetailDTO.setUserImg(user.getUserImg());
        }
    }

    @Override
    public OutputProjectAppriaiseDetailDTO findById(String id) {
        OutputProjectAppriaiseDetailDTO response = new OutputProjectAppriaiseDetailDTO();
        ProjectAppraise detail = baseMapper.selectById(Long.valueOf(id));
        if(detail==null){
            return null;
        }
        BeanUtils.copyProperties(detail,response);
        return response;
    }

   @Override
    public String delById(InputProjectAppraiseDetailDTO dto) {
        ProjectAppraise detail = baseMapper.selectById(Long.valueOf(dto.getId()));
        if(detail==null){
            return OutEnum.WARN.getCode();
        }

        detail.setStatus(ComEnum.IsDelete.DEL.getValue());
        detail.setUpdateDate(new Date());
        detail.setUpdateBy(dto.getUpdateBy());
        int success = baseMapper.updateById(detail);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return OutEnum.FAIL.getCode();
    }

    @Override
    public String updateDetail(InputProjectAppraiseDetailDTO dto) {
        ProjectAppraise entity = baseMapper.selectById(Long.valueOf(dto.getId()));
        if(entity==null){
            return OutEnum.WARN.getCode();
        }
        ProjectAppraise detail =new ProjectAppraise();
        BeanUtils.copyProperties(dto,detail);
        detail.setUpdateDate(new Date());
        detail.setUpdateBy(dto.getUpdateBy());
        detail.setCreateBy(entity.getBranch());
        detail.setCreateDate(entity.getCreateDate());
        detail.setBranch(entity.getBranch());
        detail.setBranchName(entity.getBranchName());
        detail.setChannel(entity.getChannel());
        int success = baseMapper.updateById(detail);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return OutEnum.FAIL.getCode();
    }

    @Override
    public String addEntity(InputProjectAppraiseAddDTO dto) {

        if(dto.getProjectId()==null){
            return  OutEnum.MUSTPARAMS.getMessage();
        }

        OutputProjectMainInfoDTO outputProjectMainInfoDTO= this.projectService.findMainInfoByProjectId(dto.getProjectId()+"");
        if(outputProjectMainInfoDTO==null){
            return "项目信息有误";
        }
        ProjectAppraise detail =new ProjectAppraise();
        BeanUtils.copyProperties(dto,detail);

        if(ProjectEnum.ProjectAppraiseType.REPLAY_APPRISE.getValue().equals(detail.getAppraiseType())){
            String oldAppraiseId= detail.getAppraiseId();
            if(StringUtils.isEmpty(oldAppraiseId)){
                return  OutEnum.MUSTPARAMS.getMessage();
            }
            if(this.baseMapper.selectById(oldAppraiseId)==null){
                return "原评价信息有误";

            }
        }
        detail.setUpdateDate(new Date());
        detail.setUpdateBy(dto.getCreateBy());
        detail.setCreateBy(dto.getCreateBy());
        detail.setCreateDate(new Date());
        detail.setBranch(dto.getBranch()==null?ComEnum.Branch.YUN_TOU.getValue():dto.getBranch());
        detail.setBranchName(dto.getBranchName()==null?ComEnum.Branch.YUN_TOU.getName():dto.getBranchName());
        detail.setChannel(dto.getChannel()==null?ComEnum.Channel.WEB.getValue():dto.getChannel());
        detail.setStatus(ComEnum.IsDelete.NORMAL.getValue());
        int success = baseMapper.insert(detail);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return OutEnum.FAIL.getCode();
    }


}
