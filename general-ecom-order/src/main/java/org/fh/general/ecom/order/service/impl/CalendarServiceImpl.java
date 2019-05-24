package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.calendar.CalendarListInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.CalendarListOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.DetailCalendarHeadInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.DetailCalendarHeadOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgDetailPageInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgDetailPageOutputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgFormulaPageInputDTO;
import org.fh.general.ecom.common.dto.order.calendar.FindBgFormulaPageOutputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.SumOnePlanAmountInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.calendar.CalendarListOutPO;
import org.fh.general.ecom.common.po.order.calendar.InDaysInputDTO;
import org.fh.general.ecom.common.po.order.calendar.InDaysOutputDTO;
import org.fh.general.ecom.common.po.order.calendar.InDaysPO;
import org.fh.general.ecom.common.po.order.calendar.InDaysParamPO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListInputDTO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListOutputDTO;
import org.fh.general.ecom.common.po.order.calendar.MyCalendarPO;
import org.fh.general.ecom.common.po.order.calendar.MyCalListParamPO;
import org.fh.general.ecom.common.po.order.calendar.MyCalPlanPO;
import org.fh.general.ecom.common.vo.order.calendar.CalendarListVO;
import org.fh.general.ecom.common.vo.order.projectFormula.ProjectFormulaListVO;
import org.fh.general.ecom.common.vo.product.project.ProjectOneDetailVO;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.model.Calendar;
import org.fh.general.ecom.order.dao.CalendarDao;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.model.ProjectPlan;
import org.fh.general.ecom.order.model.RedProject;
import org.fh.general.ecom.order.service.CalendarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.fh.general.ecom.order.service.ProjectPlanService;
import org.fh.general.ecom.order.service.RedProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分红日历表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Slf4j
@Service
public class CalendarServiceImpl extends ServiceImpl<CalendarDao, Calendar> implements CalendarService {

    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectFormulaService projectFormulaService;
    @Autowired
    private RedProjectService redProjectService;
    @Autowired
    private ProductClient productClient;



    @Override
    public CalendarListOutputDTO findPage(CalendarListInputDTO dto)throws Exception {
        CalendarListOutputDTO response=new CalendarListOutputDTO();
        EntityWrapper<Calendar> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        List<Calendar> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<CalendarListOutPO>  listpo=new ArrayList<CalendarListOutPO>();
        list.forEach((Calendar temp) -> {
            CalendarListOutPO po=new CalendarListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

    @Override
    public MyCalListOutputDTO findMyCalendarList(MyCalListInputDTO dto){
        MyCalListOutputDTO  resDto=new MyCalListOutputDTO();
        try {
            MyCalListParamPO  paramPO=new MyCalListParamPO();
            paramPO.setShareDay(dto.getShareDay());
            List<MyCalendarPO> calendarList=baseMapper.findMyCalendarList(paramPO);
            if(calendarList==null || calendarList.size()==0){
                return null;
            }
            calendarList.forEach((MyCalendarPO temp)->{
                List<Calendar> calList=findPlanListByCalendarPid(dto.getShareDay(),temp.getProjectId());
                if(calList!=null && calList.size()>0){
                    List<MyCalPlanPO>  planList=new ArrayList<MyCalPlanPO>();
                    calList.forEach((Calendar calEn)->{
                        MyCalPlanPO plan=new MyCalPlanPO();
                        plan.setPlanName(calEn.getPlanName());
                        plan.setShareAmount(calEn.getShareAmount());
                        planList.add(plan);
                    });
                    temp.setPlanList(planList);
                    BigDecimal total = planList.stream().map(p->p.getShareAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
                    temp.setTotal(total);
                }
            });
            resDto.setCalendarList(calendarList);
            return resDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDto;
    }

    //日期+projectId=查询方案列表
    public List<Calendar>  findPlanListByCalendarPid(String  shareDay,Long projectId){
        EntityWrapper<Calendar> wrapper = new EntityWrapper<Calendar>();
        wrapper.eq("share_day",shareDay);
        wrapper.eq("project_id",projectId);
        List<Calendar> list=baseMapper.selectList(wrapper);
        return list;
    }


    @Override
    public InDaysOutputDTO  findInDays(InDaysInputDTO inputDTO){
        InDaysParamPO paramPO=new InDaysParamPO();
        BeanUtils.copyProperties(inputDTO,paramPO);
         List<InDaysPO> dayList= this.baseMapper.findInDays(paramPO);
        InDaysOutputDTO resDato=new InDaysOutputDTO();
        resDato.setDaysList(dayList);
        return resDato;
    }


    @Override
    public String addEntity(Long redProjectId)  throws Exception{
        String code="";
        try {

            //先按试算日期分红——》在新增分红日历（历史）
            EntityWrapper<ProjectPlan> wrapperPlan = new EntityWrapper<ProjectPlan>();
            wrapperPlan.eq("red_project_id",redProjectId);
            List<ProjectPlan> planList=this.projectPlanService.selectList(wrapperPlan);
            if(planList==null || planList.size()==0){
                return "方案查无数据,请检查分红项目（"+redProjectId+"）是否有方案（tb_red_project_plan）。";
            }

            planList.forEach((ProjectPlan plan)->{
                Calendar calendarEn=new Calendar();
                calendarEn.setRedProjectId(redProjectId);
                calendarEn.setPlanId(plan.getPlanId());
                calendarEn.setPlanName(plan.getPlanName());
                calendarEn.setCurrentRedYearRate(plan.getRateWhite());
                calendarEn.setShareTime(new Date());//分红日期

                //分红金额/万元
                SumOnePlanAmountInputDTO one =new SumOnePlanAmountInputDTO();
                one.setSuccessStatus("2");
                one.setPlanId(plan.getPlanId());
                BigDecimal amount=this.projectFormulaService.sumOnePlanAmount(one);
                calendarEn.setShareAmount(amount.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP));
                this.baseMapper.insert(calendarEn);
            });
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }


    public FindBgDetailPageOutputDTO findBgDetailPage(FindBgDetailPageInputDTO dto) {
        FindBgDetailPageOutputDTO  response=new FindBgDetailPageOutputDTO();
        //2）分页信息
        EntityWrapper<Calendar> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        wrapper.eq("red_project_id",dto.getRedProjectId());
        List<Calendar> list=baseMapper.selectList(wrapper);
        if(list==null || list.size()==0){
            log.info("分红项目ID["+dto.getRedProjectId()+"]查询分红日历(tb_calendar)查无数据");
            return null;
        }
        PageInfo pageInfo=new PageInfo(list);
        List<CalendarListVO>  listEn=new ArrayList<CalendarListVO>();
        list.forEach((Calendar temp) -> {
            CalendarListVO en=new CalendarListVO();
            BeanUtils.copyProperties(temp,en);
            BigDecimal shareAmount=temp.getShareAmount().divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
            en.setShareAmount(shareAmount);//转化为万元
            listEn.add(en);
        });
        response.setCalendarList(listEn);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public DetailCalendarHeadOutputDTO calendarHead(DetailCalendarHeadInputDTO dto){
        DetailCalendarHeadOutputDTO response=new DetailCalendarHeadOutputDTO();
        //1)基本信息
        RedProject redProject=this.redProjectService.selectById(dto.getRedProjectId());
        if(redProject==null){
            log.info("分红项目ID["+dto.getRedProjectId()+"]有误，查无数据");
            return null;
        }
        ProjectOneDetailVO mainProject=this.productClient.findDetailByProjectId(redProject.getProjectId());
        if(mainProject==null){
            log.info("项目编号["+redProject.getProjectId()+"]有误，在项目基础表(tb_project)查无数据");
            return null;
        }
        response.setProjectName(mainProject.getProjectName());
        response.setCompanyName(mainProject.getCompanyName());
        response.setConcatUser(mainProject.getContacts());
        response.setConcatPhone(mainProject.getContactsTel());
        //分红明细-实际分红金额合计
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("redProjectId",dto.getRedProjectId());
        BigDecimal  sumTotalRealRedAmount=this.baseMapper.sumBenqiRealRedAmount(param);
        response.setSumTotalRealRedAmount(sumTotalRealRedAmount);
        return response;
    }


    public FindBgFormulaPageOutputDTO findBgFormulaPage(FindBgFormulaPageInputDTO dto){
        FindBgFormulaPageOutputDTO  response=new FindBgFormulaPageOutputDTO();
        Calendar  calendar=this.baseMapper.selectById(dto.getId());
        if(calendar==null){
            log.info("分红日历ID["+dto.getId()+"]有误，查无数据");
            return null;
        }

        //分页数据
        EntityWrapper<ProjectFormula> wrapper = new EntityWrapper<ProjectFormula>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        wrapper.eq("red_project_id",calendar.getRedProjectId());
        List<ProjectFormula> list=this.projectFormulaService.selectList(wrapper);
        if(list==null || list.size()==0){
            log.info("分红项目ID["+calendar.getRedProjectId()+"]查询分红日历(tb_red_project_formula)查无数据");
            return null;
        }
        PageInfo pageInfo=new PageInfo(list);
        List<ProjectFormulaListVO>  listEn=new ArrayList<ProjectFormulaListVO>();
        list.forEach((ProjectFormula temp) -> {
            ProjectFormulaListVO en=new ProjectFormulaListVO();
            BeanUtils.copyProperties(temp,en);
            listEn.add(en);
        });
        response.setFormulaList(listEn);
        response.setPageInfo(pageInfo);
        return response;
    }


}
