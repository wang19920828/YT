package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditAddInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListInputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.RedAuditListOutputDTO;
import org.fh.general.ecom.common.dto.order.redAudit.SureRedAuditInputDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.enumeration.order.RedProjectEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogShowPO;
import org.fh.general.ecom.common.po.order.projectFormula.ProjectFormulaShowPO;
import org.fh.general.ecom.common.po.order.projectPlan.ProjectPlanShowPO;
import org.fh.general.ecom.common.po.order.redAudit.RedAuditListOutPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.model.AuditLog;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.model.ProjectPlan;
import org.fh.general.ecom.order.model.RedAudit;
import org.fh.general.ecom.order.dao.RedAuditDao;
import org.fh.general.ecom.order.service.AuditLogService;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.fh.general.ecom.order.service.ProjectPlanService;
import org.fh.general.ecom.order.service.RedAuditService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分红审核表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Slf4j
@Service
public class RedAuditServiceImpl extends ServiceImpl<RedAuditDao, RedAudit> implements RedAuditService {



    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectFormulaService projectFormulaService;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private TimedTaskShareRed timedTaskShareRed;
    @Autowired
    private ProductClient productClient;



    @Override
    public RedAuditListOutputDTO findPage(RedAuditListInputDTO dto)throws Exception {
        RedAuditListOutputDTO response=new RedAuditListOutputDTO();
        EntityWrapper<RedAudit> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );

        if(StringUtils.isNotEmpty(dto.getProjectName())){
            wrapper.like("project_name",dto.getProjectName());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyName())){
            wrapper.like("company_name",dto.getCompanyName());
        }
        if(StringUtils.isNotEmpty(dto.getCurrentTimeStart()) || StringUtils.isNotEmpty(dto.getCurrentTimeEnd())){
            wrapper.between("current_time",dto.getCurrentTimeStart(),dto.getCurrentTimeEnd());
        }
        if(StringUtils.isNotEmpty(dto.getAddTimeStart()) || StringUtils.isNotEmpty(dto.getAddTimeEnd())){
            wrapper.between("add_time",dto.getAddTimeStart(),dto.getAddTimeEnd());
        }

        List<RedAudit> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<RedAuditListOutPO>  listpo=new ArrayList<RedAuditListOutPO>();
        list.forEach((RedAudit temp) -> {
            RedAuditListOutPO po=new RedAuditListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(RedAuditAddInputDTO dto)  throws Exception{
        String code="";
        try {
            RedAudit entity=new RedAudit();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }

    @Override
    public RedAuditBgDetailOutputDTO detailBgRedAudit(Long id) {
        RedAuditBgDetailOutputDTO  dto= new RedAuditBgDetailOutputDTO();
        RedAudit audit=baseMapper.selectById(id);
        if(audit==null){
            log.info("ID["+id+"]信息有误，审核申请对象查无数据！");
            return null;
        }
        dto.setCurrentTime(DateUtils.formatDate(audit.getCurrentTime(),DateUtils.DATE_FROMAT1));
        dto.setProjectName(audit.getProjectName());
        Long redProjectId=audit.getRedProjectId();

        EntityWrapper<ProjectPlan> wrapperPlan = new EntityWrapper<>();
        wrapperPlan.eq("red_project_id",redProjectId);
        List<ProjectPlan> planList=this.projectPlanService.selectList(wrapperPlan);
        if(planList !=null && planList.size()>0){
            List<ProjectPlanShowPO> planPoList=new ArrayList<ProjectPlanShowPO>();
            planList.forEach((ProjectPlan temp)->{
                ProjectPlanShowPO planPo=new ProjectPlanShowPO();
                BeanUtils.copyProperties(temp,planPo);
                planPoList.add(planPo);
            });
            dto.setPlanList(planPoList);
        }

        EntityWrapper<ProjectFormula> wrapperFormula= new EntityWrapper<>();
        wrapperFormula.eq("red_project_id",redProjectId);
        List<ProjectFormula> formulaList=this.projectFormulaService.selectList(wrapperFormula);
        if(formulaList !=null && formulaList.size()>0){
            List<ProjectFormulaShowPO> fPoList=new ArrayList<ProjectFormulaShowPO>();
            formulaList.forEach((ProjectFormula temp)->{
                ProjectFormulaShowPO formulaPo=new ProjectFormulaShowPO();
                BeanUtils.copyProperties(temp,formulaPo);
                fPoList.add(formulaPo);
            });
            dto.setFormulaList(fPoList);
        }
        BigDecimal preRedAmount = formulaList.stream().map(p->p.getBenqiPreRedAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal realRedAmount = formulaList.stream().map(p->p.getBenqiRealRedAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalPreRedAmount(preRedAmount);
        dto.setTotalRealRedAmount(realRedAmount);

        //审核日志
        EntityWrapper<AuditLog> logWrapper= new EntityWrapper<AuditLog>();
        logWrapper.eq("type","1");//类型：1-分红审核日志 2-提现审核日志 3-退款审核日志
        logWrapper.eq("object_id",audit.getProjectId());
        List<AuditLog> logList=this.auditLogService.selectList(logWrapper);
        if(logList!=null && logList.size()>0){
            List<AuditLogShowPO> fPoList=new ArrayList<AuditLogShowPO>();
            logList.forEach((AuditLog temp)->{
                AuditLogShowPO logPO=new AuditLogShowPO();
                BeanUtils.copyProperties(temp,logPO);
                fPoList.add(logPO);
            });
            dto.setAuditLogList(fPoList);
        }

        return dto;
    }

    @Override
    @Transactional
    public  String sureRedAudit(SureRedAuditInputDTO dto) {
        try {

            RedAudit redAudit=baseMapper.selectById(dto.getAuditId());
            if(redAudit==null){
                return "id["+dto.getAuditId()+"]上送有误，查无对象！";
            }
            redAudit.setStatus(dto.getStatus());//审核状态：1-待审核 2-通过 3-驳回
            redAudit.setUserName(dto.getAuditName());
            redAudit.setZhidingTime(dto.getZhidingTime());
            baseMapper.updateById(redAudit);

            //记录操作日志-operaLog
            String status=redAudit.getStatus()=="2"?"":"3";
            this.insertProjectOperLog(Long.valueOf(redAudit.getProjectId()),status);


            //记录日志
            AuditLogAddInputDTO logDto=new AuditLogAddInputDTO();
            BeanUtils.copyProperties(dto,logDto);
            logDto.setObjectId(redAudit.getProjectId());
            logDto.setAddTime(new Date());
            logDto.setType("1");
            logDto.setBusiness("分红审核");
            logDto.setRedTime(dto.getZhidingTime());
            logDto.setResults(RedProjectEnum.RedAuditStatus.codeOf(dto.getStatus()).getName());//2-通过 3-驳回
            this.auditLogService.addEntity(logDto);

            // zhidingTime=1970-01-01 表示立即发放分红
            if("1970-01-01".equals(dto.getZhidingTime())){
                BaseVO baseVO=this.timedTaskShareRed.executeShareRed();
                if(!OutEnum.SUCCESS.getCode().equals(baseVO.getMsg().getCode())){
                    log.info("【立即发放分红失败！】");
                    return baseVO.getMsg().getCode();
                }
            }

            return OutEnum.SUCCESS.getCode();
        } catch (Exception e) {
            e.printStackTrace();
            return OutEnum.FAIL.getCode();
        }
    }


   /* public void insertProjectOperLog(RedAudit temp){
        InputProjectOperAddDTO operEn=new InputProjectOperAddDTO();
        operEn.setProjectId(Long.valueOf(temp.getProjectId()));
        operEn.setOperTime(DateUtils.formatDate(new Date(),DateUtils.DATE_FROMAT2));
        if("2".equals(temp.getStatus())){// 2-通过 3-驳回
            operEn.setRedStatus("4");//4-待分红
        }else{
            operEn.setRedStatus("3");//3-驳回
        }
        this.productClient.insertProjectOperLog(operEn);
    }*/

    @Override
    public void insertProjectOperLog(Long projectId,String redStatus){
        InputProjectOperAddDTO operEn=new InputProjectOperAddDTO();
        operEn.setProjectId(projectId);
        operEn.setOperTime(DateUtils.formatDate(new Date(),DateUtils.DATE_FROMAT2));
        operEn.setRedStatus(redStatus);//分红状态：1-待申请/2-待审核/3-驳回/4-待分红/5-已分红
        this.productClient.insertProjectOperLog(operEn);
    }







}
