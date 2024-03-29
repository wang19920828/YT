package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.basics.user.UserOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.electronicsAccount.ElectronicsAccountOutputDTO;
import org.fh.general.ecom.common.dto.order.order.FindListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.JiSuanShareRedAmountInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.BatchInsertRedProjectInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplySubmitInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedApplyTryOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectAddInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectBgDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListInputDTO;
import org.fh.general.ecom.common.dto.order.redProject.RedProjectListOutputDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.enumeration.order.FormulaEnum;
import org.fh.general.ecom.common.enumeration.order.OrderEnum;
import org.fh.general.ecom.common.enumeration.order.RedProjectEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogListOutPO;
import org.fh.general.ecom.common.po.order.order.OrderPO;
import org.fh.general.ecom.common.po.order.orderProduct.OpcountPO;
import org.fh.general.ecom.common.po.order.projectFormula.SumFormulaByPlanIdPO;
import org.fh.general.ecom.common.po.order.redProject.GetAmountLeijiInputPO;
import org.fh.general.ecom.common.po.order.redProject.RaisePlanPO;
import org.fh.general.ecom.common.po.order.redProject.RedProjectListOutPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.ToolUtils;
import org.fh.general.ecom.common.utils.VerifyUtils;
import org.fh.general.ecom.common.vo.order.projectFormula.ProjectFormulaListVO;
import org.fh.general.ecom.common.vo.product.project.PlanEntityVO;
import org.fh.general.ecom.common.vo.product.project.ProjectOneDetailVO;
import org.fh.general.ecom.common.vo.product.project.ProjectProgrammeVO;
import org.fh.general.ecom.order.client.BasicsClient;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.model.AuditLog;
import org.fh.general.ecom.order.model.Order;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.model.RedProject;
import org.fh.general.ecom.order.dao.RedProjectDao;
import org.fh.general.ecom.order.service.AuditLogService;
import org.fh.general.ecom.order.service.OrderProductService;
import org.fh.general.ecom.order.service.OrderService;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.fh.general.ecom.order.service.RedAuditService;
import org.fh.general.ecom.order.service.RedProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分红项目管理表 服务实现类
 * </p>
 *detailBgRedProject
 * @author pjj
 * @since 2018-09-14
 */
@Slf4j
@Service
public class RedProjectServiceImpl extends ServiceImpl<RedProjectDao, RedProject> implements RedProjectService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProjectFormulaService projectFormulaService;
    @Autowired
    private BasicsClient basicsClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedAuditService redAuditService;

    @Override
    public RedProjectListOutputDTO findPage(RedProjectListInputDTO dto)throws Exception {
        RedProjectListOutputDTO response=new RedProjectListOutputDTO();
        EntityWrapper<RedProject> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );

        if(StringUtils.isNotEmpty(dto.getProjectName())){
            wrapper.like("project_name",dto.getProjectName());
        }
        if(StringUtils.isNotEmpty(dto.getCompanyName())){
            wrapper.like("company_name",dto.getCompanyName());
        }
        if(StringUtils.isNotEmpty(dto.getShareStatus())){
            wrapper.eq("share_status",dto.getShareStatus());
        }
        if(StringUtils.isNotEmpty(dto.getOverStatus())){
            wrapper.eq("over_status",dto.getOverStatus());
        }
        if(StringUtils.isNotEmpty(dto.getCurrentTimeStart()) || StringUtils.isNotEmpty(dto.getCurrentTimeEnd())){
            wrapper.between("current_time",dto.getCurrentTimeStart(),dto.getCurrentTimeEnd());
        }

        List<RedProject> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<RedProjectListOutPO>  listpo=new ArrayList<RedProjectListOutPO>();
        list.forEach((RedProject temp) -> {
            RedProjectListOutPO po=new RedProjectListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    /**
     * 批量生成多条分红项目
     * @param dto
     * @return
     */
    @Override
    public String batchInsertRedProject(BatchInsertRedProjectInputDTO dto){
        String code="";
        String projectId=dto.getProjectId();
        String batchNumber="RED"+ToolUtils.getRandomString(5, VerifyUtils.RANDOM_RANGE);
        ProjectOneDetailVO  project=this.productClient.findDetailByProjectId(projectId);
        if(project==null){
            return "项目ID["+projectId+"]有误，查不到基础数据请检查tb_project表";
        }
        String cycle=project.getRedTerm();
        if(StringUtils.isEmpty(cycle)){
            return "项目["+projectId+"]周期有误，不能为空";
        }
        Integer count=12/Integer.valueOf(cycle);//次数
        for(int i=0;i<count;i++){
            Integer  someMonth=Integer.valueOf(cycle)*i;
            String shareDay=DateUtils.getAfterSomeMonth2(someMonth,1);
            log.info("【项目["+project.getProjectName()+"]的第（"+(i+1)+"）次分红日期为"+shareDay+"】");
            Date  currentTime=DateUtils.getDate(shareDay,DateUtils.DATE_FROMAT1);
            RedProject entity=new RedProject();
            entity.setCurrentTime(currentTime);
            entity.setProjectName(project.getProjectName());
            entity.setExp1(project.getRedTerm());
            entity.setCompanyId(Long.valueOf(project.getCompanyId()));
            entity.setCompanyName(project.getCompanyName());
            entity.setProjectId(projectId);
            entity.setAddTime(new Date());
            entity.setAmountReal(dto.getRealRaiseAmount());
            entity.setOverStatus(RedProjectEnum.OverStatus.NORMAL.getValue());
            entity.setShareStatus(RedProjectEnum.ShareStatus.WAIT_APPLY.getValue());
            BigDecimal benqi=this.getBenQiShareAmount(projectId,cycle);
            entity.setAmountExpected(benqi);
            entity.setExp2(batchNumber);
            entity.setBranch(dto.getBranch());
            log.info("["+project.getProjectName()+"]的所有购买方案累加得到的'预期本期待分红额'为("+benqi+")元");
            baseMapper.insert(entity);
        }
        code=OutEnum.SUCCESS.getCode();
        return code;
    }


    //累加获取该项目所有方案在本期的分红总金额
    public BigDecimal getBenQiShareAmount(String projectId,String cycle) {
        try {
            FindListInputDTO orderParam=new FindListInputDTO();
            orderParam.setOrderStatus(OrderEnum.OrderStataus.ORDER_STATUS_BUYSUCCESS.getValue());
            orderParam.setPayStatus(OrderEnum.PayStatus.PAY_STATUS_OK.getValue());
            orderParam.setProductId(projectId);
            List<OrderPO>  orderList=this.orderService.findList(orderParam);
            if(orderList==null || orderList.size()==0){
                log.info("【项目id为["+projectId+"]的项目没有满足分红条件的订单：已支付，认购成功】");
                return new BigDecimal(0.00);
            }

            List<BigDecimal> bigssList=new ArrayList<BigDecimal>();
            orderList.forEach((OrderPO temp)->{
                BigDecimal investAmount=new BigDecimal(0.00);
                if(OrderEnum.OrderType.APPOINTMENTAMOUNT_BUY.getValue().equals(temp.getOrderType())){
                    //预约认购尾款订单
                    investAmount=this.orderService.getTwoPartAllPrice(temp.getOrderSn());
                }else if(OrderEnum.OrderType.OPEN_BUY.getValue().equals(temp.getOrderType())){
                    //公开认购订单
                    investAmount=temp.getAllPrice();
                }else{
                    //预约款项 忽略
                }

                PlanEntityVO planEn=this.productClient.findProgrammeEntityById(temp.getPlanId());
                if(investAmount.compareTo(BigDecimal.ZERO)>0  && planEn!=null && planEn.getMinRedRate().compareTo(BigDecimal.ZERO)>0){
                    //每个方案的分红额
                    JiSuanShareRedAmountInputDTO  shareParams=new JiSuanShareRedAmountInputDTO();
                    shareParams.setYearRate(planEn.getMinRedRate());
                    shareParams.setCycle(new BigDecimal(cycle));
                    shareParams.setAllPrice(investAmount);
                    BigDecimal  onePlanAmount=this.projectFormulaService.jiSuanShareRedAmount(shareParams);
                    bigssList.add(onePlanAmount);
                }
            });
            BigDecimal bigss=new BigDecimal(0.00);
            for(BigDecimal big:bigssList){
                bigss=bigss.add(big);
            }
            return bigss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BigDecimal(0.00);
    }


    /**
     * 添加分红项目
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public String addEntity(RedProjectAddInputDTO dto)  throws Exception{
        String code="";
        try {
            RedProject entity=new RedProject();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }


    /**
     * 分红项目详情页
     * tb_project  项目;tb_project_programme  方案
     * @param redProjectId
     * @return
     */
    @Override
    public RedProjectBgDetailOutputDTO detailBgRedProject(Long redProjectId) {
        RedProjectBgDetailOutputDTO dto=new RedProjectBgDetailOutputDTO();
        RedProject redProject=baseMapper.selectById(redProjectId);
        if(redProject==null){
            log.info("入参id["+redProjectId+"]有误，在表tb_red_project查不到分红项目");
            return null;
        }
        //1)基本信息
        ProjectOneDetailVO  mainProject=this.productClient.findDetailByProjectId(redProject.getProjectId());
        if(mainProject==null){
            log.info("项目编号["+redProject.getProjectId()+"]有误，在项目基础表(tb_project)查无数据");
            return null;
        }
        dto.setProjectName(mainProject.getProjectName());
        dto.setCompanyName(mainProject.getCompanyName());
        dto.setConcatUser(mainProject.getContacts());
        dto.setConcatPhone(mainProject.getContactsTel());
        dto.setShareCycle(mainProject.getRedTerm());

        //2)筹资方案及分红计划
        List<PlanEntityVO> pgList=this.productClient.findProjectProgrammeList(mainProject.getId());
        if(pgList==null && pgList.size()==0){
            log.info("项目ID为["+mainProject.getId()+"]的项目没有创建项目权益方案(tb_project_programme)");
            return null;
        }
        List<RaisePlanPO> equityList=new ArrayList<RaisePlanPO>();
        pgList.forEach((PlanEntityVO temp)->{
            RaisePlanPO plan=new RaisePlanPO();
            plan.setPlanName(temp.getProgrammeName());
            BigDecimal rate=temp.getMinRedRate().multiply(new BigDecimal(100)).setScale(BigDecimal.ROUND_HALF_UP,2);//最低收益率 5.00
            plan.setPreYearRate(rate+"%");
            OpcountPO countPo=new  OpcountPO();
            countPo.setPlanId(temp.getId()+"");
            RaisePlanPO countEn =this.orderProductService.getOpCount(countPo);
            if(countEn !=null){
                //实际认购份数
                plan.setRealNum(countEn.getRealNum());
                //投资人数量
                plan.setPeopleNum(countEn.getPeopleNum());
                //实际筹资金额/万元
                plan.setRealRaiseAmount(countEn.getRealRaiseAmount());

                SumFormulaByPlanIdPO sumEn=this.projectFormulaService.sumFormulaByPlanId(temp.getId()+"");
                if(sumEn!=null){
                    //累计已分红/万元
                    plan.setLeijiHaveShareAmount(sumEn.getRealAmount());
                    //预期剩余待分红/万元
                    plan.setLeaveShareAmount(sumEn.getPreAmount());
                }
                equityList.add(plan);
            }else{
                log.info("统计planid为["+temp.getId()+"]的(筹资方案及分红计划)明细有误");
            }
            //leijiShareRed =leijiShareRed.add(plan.getLeijiHaveShareAmount());
            //realRaiseAmount =realRaiseAmount.add(plan.getLeaveShareAmount());
        });
        //利用Stream聚合函数对BigDecimal求和
        BigDecimal leijiShareRed = equityList.stream().map(p->p.getLeijiHaveShareAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal realRaiseAmount = equityList.stream().map(p->p.getLeaveShareAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setEquityList(equityList);
        dto.setLeijiShareRed(leijiShareRed.divide(new BigDecimal(10000)).setScale(BigDecimal.ROUND_HALF_UP,2));//统计实际筹资金额/万元
        dto.setRealRaiseAmount(realRaiseAmount.divide(new BigDecimal(10000)).setScale(BigDecimal.ROUND_HALF_UP,2));//统计累计已分红/万元

        //3)分红操作日志
        EntityWrapper<AuditLog> wrapper = new EntityWrapper<AuditLog>();
        wrapper.eq("object_id",redProject.getProjectId());
        wrapper.eq("type","1");
        List<AuditLog> logList=this.auditLogService.selectList(wrapper);
        List<AuditLogListOutPO> logPoList=new ArrayList<AuditLogListOutPO>();
        if(logList!=null && logList.size()>0){
            logList.forEach((AuditLog temp)->{
                AuditLogListOutPO logPo=new AuditLogListOutPO();
                BeanUtils.copyProperties(temp,logPo);
                logPoList.add(logPo);
            });
            dto.setLogList(logPoList);
        }

        return dto;
    }


    public RedApplyTryOutputDTO redApplyTry(RedApplyTryInputDTO dto){
        RedApplyTryOutputDTO outDto=new RedApplyTryOutputDTO();
        String planArr=dto.getPlanArr();
        if(StringUtils.isEmpty(planArr)){
            String msg="入参有误planArr不能为空";
            log.info(msg);
            outDto.exception(msg);
            return outDto;
        }

        RedProject redProject=this.baseMapper.selectById(dto.getRedProjectId());
        if(redProject==null){
            outDto.exception("分红项目主键redProjectId=["+dto.getRedProjectId()+"]有误，查无数据");
            return outDto;
        }
        //分红方案
        List<ProjectFormulaListVO> formulaList=new ArrayList<ProjectFormulaListVO>();
        String[] planIdsArr=planArr.split(";");
        for(String planIds:planIdsArr){
            String[]  entity=planIds.split(",");
            Long planId=Long.valueOf(entity[0]);
            String programmeName=entity[1];
            BigDecimal inputRate=new BigDecimal(entity[2]);

            EntityWrapper<Order> wrapper = new EntityWrapper<Order>();
            wrapper.eq("plan_id", planId);
            wrapper.eq("order_status", "6");
            wrapper.eq("is_del", "0");
            List<Order> opList = this.orderService.selectList(wrapper);
            if (opList != null && opList.size() > 0) {
                //for(Order order:opList){
                    for(int i=0;i<opList.size();i++){
                        Order order=opList.get(i);
                    ElectronicsAccountOutputDTO account=this.basicsClient.findAccountByUserId(order.getUserId());
                    if(account==null || StringUtils.isEmpty(account.getAccountNo())){
                        String msg="用户["+order.getUserId()+"]，未开通电子账号！";
                        log.info(msg);
                        outDto.exception(msg);
                        return outDto;
                    }
                    if( !OrderEnum.OrderType.APPOINTMENTAMOUNT.getValue().equals(order.getOrderType())){
                        //非预约单
                        ProjectFormulaListVO formula = new ProjectFormulaListVO();
                        formula.setNumbers(Long.valueOf((i+1)));
                        formula.setAllNumbers(Long.valueOf(opList.size()));
                        BigDecimal  investAmount=order.getAllPrice();
                        if(OrderEnum.OrderType.APPOINTMENTAMOUNT_BUY.getValue().equals(order.getOrderType())){
                            //预约认购尾款订单
                            investAmount=this.orderService.getTwoPartAllPrice(order.getOrderSn());
                        }
                        formula.setRedProjectId(dto.getRedProjectId());
                        formula.setProductId(Long.valueOf(redProject.getProjectId()));
                        formula.setPlanId(planId);
                        formula.setPlanName(programmeName);
                        formula.setInvestId(Long.valueOf(order.getUserId()));
                        formula.setInvestName("玛提尔德");
                        UserOutputDTO cutomer = this.basicsClient.findCustomerByUserId(formula.getInvestId());
                        if (cutomer != null && StringUtils.isNotEmpty(cutomer.getRealName())) {
                            formula.setInvestName(cutomer.getRealName());
                        }
                        formula.setAmountInvest(investAmount);

                        GetAmountLeijiInputPO  leijiPo=new GetAmountLeijiInputPO();
                        leijiPo.setPlanId(order.getPlanId()+"");
                        leijiPo.setExp1(FormulaEnum.ShareRedStatus.RED_STATUS_SUCCESS.getValue());
                        BigDecimal amountLeiji=this.baseMapper.getAmountLeiji(leijiPo);
                        formula.setAmountLeiji(amountLeiji);

                        //A) min
                        JiSuanShareRedAmountInputDTO preDto=new JiSuanShareRedAmountInputDTO();
                        preDto.setAllPrice(investAmount);
                        preDto.setCycle(new BigDecimal(redProject.getExp1()));
                        //获取plan的最低年化收益率
                        PlanEntityVO plan=this.productClient.findProgrammeEntityById(planId);
                        if(plan==null){
                            String msg="方案["+planId+"]基本信息表（tb_project_programme）有误，查无数据！";
                            log.info(msg);
                            outDto.exception(msg);
                            return outDto;
                        }
                        BigDecimal minRate=plan.getMinRedRate();
                        preDto.setYearRate(minRate);
                        BigDecimal minRedAmount=this.projectFormulaService.jiSuanShareRedAmount(preDto);

                        //B）input
                        JiSuanShareRedAmountInputDTO realDto=new JiSuanShareRedAmountInputDTO();
                        realDto.setAllPrice(investAmount);
                        realDto.setCycle(new BigDecimal(redProject.getExp1()));
                        realDto.setYearRate(inputRate);
                        BigDecimal realRedAmount=this.projectFormulaService.jiSuanShareRedAmount(realDto);


                        //A）预期=拿最低年化收益率计算
                        formula.setBenqiPreYearRate(minRate);//本期预期年化收益率（eg：值20页面展示为20%）
                        formula.setBenqiPreRedAmount(minRedAmount);//本期分红金额（元）

                        //B）本期实际=拿页面输入的年化收益率计算
                        formula.setBenqiRealYearRate(inputRate);
                        formula.setBenqiRealRedAmount(realRedAmount);

                        formula.setOrderSn(order.getOrderSn());
                        formula.setPreShareTime(redProject.getCurrentTime());
                        formula.setAccountNo(account.getAccountNo()==null?"11111111":account.getAccountNo());
                        formula.setAddTime(new Date());
                        formulaList.add(formula);
                    }
                };
            }else{
                log.info("【警告，方案["+programmeName+"]在订单表里面查无数据，不参与试算!】");
            }
        }

        //分页
        if(formulaList!=null && formulaList.size()>0){
            BigDecimal benqi_pre_red_amount = formulaList.stream().map(p->p.getBenqiPreRedAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            outDto.setPreShareRedTotal(benqi_pre_red_amount);
            PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
            PageInfo pageInfo=new PageInfo(formulaList);
            outDto.setFormulaList(formulaList);
            outDto.setPageInfo(pageInfo);
            outDto.success();
            return outDto;
        }else{
            String msg="【项目["+redProject.getProjectName()+"]的所有方案在订单表里没有一个被成功购买过】";
            log.info(msg);
            outDto.exception(msg);
            return outDto;
        }
    }


    @Override
    public String redApplySubmit(RedApplySubmitInputDTO dto){
        RedApplyTryInputDTO  tryInputDTO=new RedApplyTryInputDTO();
        tryInputDTO.setCurrentPageNum(1);
        tryInputDTO.setPageCount(1000);
        tryInputDTO.setPlanArr(dto.getPlanArr());
        tryInputDTO.setRedProjectId(dto.getRedProjectId());
        RedApplyTryOutputDTO  tryEn=this.redApplyTry(tryInputDTO);

        List<ProjectFormulaListVO> forList=tryEn.getFormulaList();
        if(forList==null || forList.size()==0){
            return "（分红试算列表）有误查无数据";
        }
        forList.forEach((ProjectFormulaListVO temp)->{
            ProjectFormula entity=new ProjectFormula();
            BeanUtils.copyProperties(temp,entity);
            this.projectFormulaService.insert(entity);
            log.info("【分红试算列表——》成功存入一条试算数据!投资人是("+entity.getInvestName()+")，方案名("+entity.getPlanName()+")】");

            //操作日志operaLog
            this.redAuditService.insertProjectOperLog(temp.getProductId(),"2");
        });
        return OutEnum.SUCCESS.getCode();
    }


}
