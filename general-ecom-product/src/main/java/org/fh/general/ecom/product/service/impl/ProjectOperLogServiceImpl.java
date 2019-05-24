package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.dto.basics.admin.AdminDetailOutputDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOperUserListDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOrderDetailDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.OutputProjectLogDetailDTO;
import org.fh.general.ecom.common.dto.product.projectlog.OutputProjectOperLogListDTO;
import org.fh.general.ecom.common.dto.product.projectlog.UpdateOperaLogInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.product.client.OrderClient;
import org.fh.general.ecom.product.client.BasicClient;
import org.fh.general.ecom.product.dao.ProjectOperLogDao;
import org.fh.general.ecom.product.model.ProjectOperLog;
import org.fh.general.ecom.product.service.ProjectOperLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-20
 */
@Service
public class ProjectOperLogServiceImpl extends ServiceImpl<ProjectOperLogDao, ProjectOperLog> implements ProjectOperLogService {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private BasicClient userClient;
    @Override
    public OutputProjectOperLogListDTO findPage(InputProjectOperLogListDTO dto) {
        OutputProjectOperLogListDTO outputProjectOperLogListDTO = new OutputProjectOperLogListDTO();
        EntityWrapper wrapper=new EntityWrapper();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageSize() );
        if(StringUtils.isNotEmpty(dto.getProjectId())){
            wrapper.eq("project_id",dto.getProjectId());
        }
        wrapper.orderBy("oper_time",false);


        List<ProjectOperLog> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<OutputProjectLogDetailDTO>  listpo=new ArrayList<OutputProjectLogDetailDTO>();
        list.forEach((ProjectOperLog temp) -> {
            OutputProjectLogDetailDTO dto1=new OutputProjectLogDetailDTO();
            BeanUtils.copyProperties(temp,dto1);
            if(dto1.getProjectStatus().equals(ProjectEnum.ProjectStatus.APPOINTMENT.getValue())){
                OutputOrderDetailDTO detailDTO = orderClient.findAppointmentAmountByProjectId(dto1.getProjectId()+"");
                if(detailDTO!=null) {
                    BigDecimal amount =  detailDTO.getAmount()==null ? BigDecimal.ZERO:detailDTO.getAmount();
                    BigDecimal appointmentSchedule = amount.divide(temp.getTotalAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    dto1.setAppointmentAmount(amount);
                    dto1.setAppointmentSchedule(appointmentSchedule);
                    temp.setAppointmentAmount(amount);
                    temp.setAppointmentSchedule(appointmentSchedule);
                    baseMapper.updateById(temp);
                }

            }else if (dto1.getProjectStatus().equals(ProjectEnum.ProjectStatus.SUBSCRIBE.getValue())){
                OutputOrderDetailDTO detailDTO = orderClient.findBySubscribeForAmountProjectId(dto1.getProjectId()+"");
                if(detailDTO!=null) {
                    setAmountAndSchedule(temp, dto1, detailDTO);
                    baseMapper.updateById(temp);
                }
            }else if (dto1.getProjectStatus().equals(ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue())){
                OutputOrderDetailDTO detailDTO = orderClient.findBySubscribeForAmountProjectId(dto1.getProjectId()+"");
                if(detailDTO!=null) {
                    setAmountAndSchedule(temp, dto1, detailDTO);
                    baseMapper.updateById(temp);
                }
            }


            AdminDetailOutputDTO adminDetailOutputDTO= this.userClient.findAdminEntityById(Long.valueOf(temp.getOperId()));
            if(adminDetailOutputDTO!=null) {
                dto1.setCreateName(adminDetailOutputDTO.getRealName());
            }else{
                dto1.setCreateName("系统");
            }

            if(StringUtils.isNotEmpty(temp.getRefundStatus())) {
                dto1.setRefundStatus(ProjectEnum.RefundStatus.codeOf(temp.getRefundStatus()).getName());
            }

            //认购状态
            if(StringUtils.isNotEmpty(temp.getSubscribeForStatus())) {
                dto1.setSubscribeForStatus(ProjectEnum.SubscribeForStatus.codeOf(temp.getSubscribeForStatus()).getName());
            }
            //预约状态
            if(StringUtils.isNotEmpty(temp.getAppointmentStatus())) {
               dto1.setAppointmentStatus(ProjectEnum.AppointmentStatus.codeOf(temp.getAppointmentStatus()).getName());
            }

            listpo.add(dto1);
        });
        outputProjectOperLogListDTO.setList(listpo);
        outputProjectOperLogListDTO.setPageInfo(pageInfo);
        return outputProjectOperLogListDTO;
    }

    private void setAmountAndSchedule(ProjectOperLog temp, OutputProjectLogDetailDTO dto1, OutputOrderDetailDTO detailDTO) {
        BigDecimal amount =  detailDTO.getAmount()==null ? BigDecimal.ZERO:detailDTO.getAmount();
        BigDecimal schedule = amount.divide(temp.getTotalAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        dto1.setSubscribeForAmount(amount);
        dto1.setSubscribeForSchedule(schedule);
        temp.setSubscribeForAmount(amount);
        temp.setSubscribeForSchedule(schedule);
    }

    @Override
    public String insertEntity(InputProjectOperAddDTO dto) {
        ProjectOperLog  projectOperLog=new ProjectOperLog();
        BeanUtils.copyProperties(dto,projectOperLog);
        projectOperLog.setOperTime(new Date());
        int success =  baseMapper.insert(projectOperLog);
        if(success>0){
            return OutEnum.SUCCESS.getCode();
        }
        return OutEnum.FAIL.getCode();
    }

    @Override
    public PagingExtensionVO findUserListPage(InputProjectOperLogListDTO dto) {
        EntityWrapper wrapper = new EntityWrapper();
        PagingExtensionVO pageingVo = orderClient.findUserListByProjectId(dto);
        return pageingVo;
    }

    @Override
    public OutputOperUserListDTO findCountUser(InputProjectOperLogListDTO dto) {
        OutputOperUserListDTO  response = orderClient.findCountUser(dto.getProjectId()+"");
        return response;
    }

    @Override
    public String updateOperaLogRenGouStatus(UpdateOperaLogInputDTO dto) {
        String code="";
        ProjectOperLog t=new ProjectOperLog();
        t.setProjectId(dto.getProjectId());
        t.setProjectStatus(dto.getProjectStatus());//3 预约中 4-认购中
        ProjectOperLog   entity=this.baseMapper.selectOne(t);
        if(entity !=null){
            entity.setSubscribeForStatus(dto.getRenGouStatus());
            if(dto.getRenGouStatus().equals("3")){
                entity.setAppointmentAmount(dto.getAmount());
            }else{
                entity.setSubscribeForAmount(dto.getAmount());
            }
            this.baseMapper.updateAllColumnById(entity);
        }
        return code;
    }



}
