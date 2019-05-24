package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.product.consulting.InputConsultingProjectAddDTO;
import org.fh.general.ecom.common.dto.product.consulting.OutputConsultingProjectTeamDTO;
import org.fh.general.ecom.common.dto.product.consulting.OutputConsultingProjectTeamListDTO;
import org.fh.general.ecom.product.dao.ConsultingProjectTeamDao;
import org.fh.general.ecom.product.model.ConsultingProjectTeam;
import org.fh.general.ecom.product.service.ConsultingProjectTeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咨询项目管理团队信息 服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-17
 */
@Service
public class ConsultingProjectTeamServiceImpl extends ServiceImpl<ConsultingProjectTeamDao, ConsultingProjectTeam> implements ConsultingProjectTeamService {

    public void insertBatchList(InputConsultingProjectAddDTO dto,Long projectId) {
       /* List<InputConsultingProjectTeamAddDTO> listDto = dto.getList();
        List<ConsultingProjectTeam> list= new ArrayList<ConsultingProjectTeam>();
        if(listDto!=null && listDto.size()>0) {
            listDto.forEach((InputConsultingProjectTeamAddDTO temp) -> {
                ConsultingProjectTeam po = new ConsultingProjectTeam();
                BeanUtils.copyProperties(temp, po);
                po.setProjectId(projectId);
                po.setStatus(ComEnum.IsDelete.NORMAL.getValue());
                po.setCreateBy(dto.getCreateBy());
                po.setCreateDate(new Date());
                po.setUpdateBy(dto.getCreateBy());
                po.setUpdateDate(new Date());
                list.add(po);
            });
            this.insertBatch(list);
        }*/
    }
    @Override
    public OutputConsultingProjectTeamListDTO findList(String id) {
        OutputConsultingProjectTeamListDTO outputConsultingProjectListDTO= new OutputConsultingProjectTeamListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("project_id",id);
        List<ConsultingProjectTeam> list=baseMapper.selectList(wrapper);
        List<OutputConsultingProjectTeamDTO>  listpo=new ArrayList<OutputConsultingProjectTeamDTO>();
        list.forEach((ConsultingProjectTeam temp) -> {
            OutputConsultingProjectTeamDTO po=new OutputConsultingProjectTeamDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        outputConsultingProjectListDTO.setConsultingProjectTeamOutputDTOList(listpo);
        return outputConsultingProjectListDTO;
    }



}
