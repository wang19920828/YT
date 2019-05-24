package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.order.order.FindListInputDTO;
import org.fh.general.ecom.common.dto.order.order.OrderEntityOutDTO;
import org.fh.general.ecom.common.dto.order.orderMy.DetailHasProjectInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.DetailHasProjectOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HasProjectInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HasProjectOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HavePayOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.HavePayOrderOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.MyOrderPageInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.MyOrderPageOutputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.UnPayOrderInputDTO;
import org.fh.general.ecom.common.dto.order.orderMy.UnPayOrderOutputDTO;
import org.fh.general.ecom.common.dto.order.orderProduct.OrderProductDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.FindFoumulListInputDTO;
import org.fh.general.ecom.common.enumeration.order.OrderEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.order.order.OrderPO;
import org.fh.general.ecom.common.po.order.order.RenGouListParamPO;
import org.fh.general.ecom.common.po.order.orderMy.*;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.product.project.ProjectOneDetailVO;
import org.fh.general.ecom.order.client.BasicsClient;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.dao.OrderDao;
import org.fh.general.ecom.order.model.Order;
import org.fh.general.ecom.order.model.OrderProduct;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.service.OrderMyService;
import org.fh.general.ecom.order.service.OrderProductService;
import org.fh.general.ecom.order.service.OrderService;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderMyServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderMyService {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private BasicsClient basicsClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProjectFormulaService projectFormulaService;

    @Override
    public MyOrderPageOutputDTO findMyOrderPage(MyOrderPageInputDTO dto) {
        MyOrderParamPO paramPO=new MyOrderParamPO();
        BeanUtils.copyProperties(dto,paramPO);
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        //1-正常 2-预约未申购 3-认购失败 4-冷静期退出  5-已失效  6-认购成功
        //1-待支付订单(正常状态+未支付)(1)
        if("1".equals(dto.getTypeFlag())){
            paramPO.setOrderStatus(OrderEnum.OrderStataus.ORDER_STATUS_NORMAL.getValue());
            paramPO.setPayStatus(OrderEnum.PayStatus.PAY_STATUS_NO.getValue());
        }
        //2-成功订单（认购成功状态+已支付）(6)
        if("2".equals(dto.getTypeFlag())){
            paramPO.setOrderStatus(OrderEnum.OrderStataus.ORDER_STATUS_BUYSUCCESS.getValue());
            paramPO.setPayStatus(OrderEnum.PayStatus.PAY_STATUS_OK.getValue());
        }
        //3-退款订单（退款申请中+ 已经退款）(3,4)
        if("3".equals(dto.getTypeFlag())){
            paramPO.setPayStatusMore("more");
        }
        //4-失效订单（失效状态）(5,2?)
        if("4".equals(dto.getTypeFlag())){
            paramPO.setOrderStatus(OrderEnum.OrderStataus.ORDER_STATUS_CANCEL.getValue());
        }
        List<MyOrderPO> list=baseMapper.findMyOrderPage(paramPO);
        if(list==null || list.size()==0){
            return null;
        }
        PageInfo pageInfo=new PageInfo(list);
        MyOrderPageOutputDTO response=new MyOrderPageOutputDTO();
        if(list !=null && list.size()>0){
            list.forEach((MyOrderPO temp)->{
                Long subTime=System.currentTimeMillis()-temp.getAddTime().getTime();
                log.info("订单["+temp.getOrderSn()+"]倒计时毫秒数为："+subTime);
                temp.setCountdown(subTime);//倒计时
            });
            response.setList(list);
            response.setPageInfo(pageInfo);
        }
        return response;
    }


    @Override
    public UnPayOrderOutputDTO findUnPayOrderDetail(UnPayOrderInputDTO dto){
        UnPayOrderOutputDTO resDto=new UnPayOrderOutputDTO();
        OrderEntityOutDTO order=this.orderService.findEntityByOrderSn(dto.getOrderSn());
        if(order==null){
            resDto.exception("订单编号["+dto.getOrderSn()+"]有误，查无数据");
            return resDto;
        }
        if(order.getPayStatus().equals(OrderEnum.PayStatus.PAY_STATUS_OK.getValue())){
            resDto.exception("您调错接口了，该订单已支付，需调接口FRO8007");
            return resDto;
        }
        OrderProductDTO opEntity=this.orderProductService.findEntityByOrderSn(dto.getOrderSn());
        if(opEntity==null || order==null){
            resDto.exception("订单["+dto.getOrderSn()+"]或者订单产品信息有误，查无数据！");
            return resDto;
        }
        ProjectOneDetailVO  project=this.productClient.findDetailByProjectId(order.getProductId());
        if(project==null){
            resDto.exception("["+order.getProductId()+"]项目基础信息表有误，查无数据");
            return resDto;
        }

        BeanUtils.copyProperties(order,resDto);
        resDto.setProjectId(order.getProductId());
        resDto.setPlanId(order.getPlanId());
        resDto.setProImg(opEntity.getProImg());
        resDto.setBuyNum(opEntity.getNum());
        resDto.setPlanName(opEntity.getProductPlan());
        resDto.setUnitPrice(opEntity.getLessMoney());
        resDto.setProjectStatus(project.getProjectStatus());
        resDto.setProjectStatusName(ProjectEnum.ProjectStatus.codeOf(project.getProjectStatus()).getName());
        if(order.getOrderType().equals(OrderEnum.OrderType.APPOINTMENTAMOUNT.getValue())){
            // 预约订单
            UnPayYuYueEntityPO entity=new UnPayYuYueEntityPO();
            entity.setAddTime(order.getAddTime());
            entity.setAllPrice(order.getAllPrice());
            entity.setInvestMoney(order.getInvestMoney());//预约总金额（eg：预约金300则预约总金额是3万）
            Long subTime=System.currentTimeMillis()-order.getAddTime().getTime();
            log.info("订单["+dto.getOrderSn()+"]倒计时毫秒数为："+subTime);
            entity.setCountdown(subTime);//倒计时
            resDto.setYuYueEntity(entity);
        }
        if(order.getOrderType().equals(OrderEnum.OrderType.APPOINTMENTAMOUNT_BUY.getValue())){
            // 预约认购订单
            UnPayAppEntityPO entity=new UnPayAppEntityPO();
            entity.setAddTime(order.getAddTime());
            entity.setInvestMoney(order.getInvestMoney());
            entity.setYuyuePrice(order.getYuyuePrice());
            entity.setAllPrice(order.getAllPrice());
            Long subTime=System.currentTimeMillis()-order.getAddTime().getTime();
            log.info("订单["+dto.getOrderSn()+"]倒计时毫秒数为："+subTime);
            entity.setCountdown(subTime);
            resDto.setAppEntity(entity);
        }
        if(order.getOrderType().equals(OrderEnum.OrderType.OPEN_BUY.getValue())){
            // 公开认购订单
            UnPayRenGouEntityPO entity=new UnPayRenGouEntityPO();
            entity.setAddTime(order.getAddTime());
            entity.setInvestMoney(order.getInvestMoney());
            entity.setAllPrice(order.getAllPrice());
            Long subTime=System.currentTimeMillis()-order.getAddTime().getTime();
            log.info("订单["+dto.getOrderSn()+"]倒计时毫秒数为："+subTime);
            entity.setCountdown(subTime);
            resDto.setRenGouEntity(entity);
        }
        resDto.success();
        return resDto;
    }

    @Override
    public HavePayOrderOutputDTO findHavePayOrderDetail(HavePayOrderInputDTO dto){
        HavePayOrderOutputDTO resDto=new HavePayOrderOutputDTO();
        OrderEntityOutDTO order=this.orderService.findEntityByOrderSn(dto.getOrderSn());
        if(order==null){
            resDto.exception("订单编号["+dto.getOrderSn()+"]有误，查无数据");
            return resDto;
        }
        if(order.getOrderStatus().equals(OrderEnum.PayStatus.PAY_STATUS_NO.getValue())){
            resDto.exception("您调错接口了，该订单未支付，需调接口FRO8006");
            return resDto;
        }
        OrderProductDTO opEntity=this.orderProductService.findEntityByOrderSn(dto.getOrderSn());
        if(opEntity==null){
            resDto.exception("订单编号["+dto.getOrderSn()+"]在tb_invest_product表查无数据");
            return resDto;
        }
        ProjectOneDetailVO project=this.productClient.findDetailByProjectId(order.getProductId());
        if(project==null){
            resDto.exception("项目["+order.getProductId()+"]基础信息有误，在tb_project表查无数据");
            return resDto;
        }
        resDto.setProImg(opEntity.getProImg());
        resDto.setBuyNum(opEntity.getNum());
        resDto.setPlanName(opEntity.getProductPlan());
        resDto.setUnitPrice(opEntity.getLessMoney());
        resDto.setProjectId(order.getProductId());
        resDto.setPlanId(order.getPlanId());
        resDto.setAcceptName(order.getAcceptName());
        resDto.setAcceptPhone(order.getAcceptPhone());
        resDto.setAddress(order.getAddress());
        resDto.setProjectName(opEntity.getProductName());
        resDto.setProjectStatus(project.getProjectStatus());
        resDto.setProjectStatusName(ProjectEnum.ProjectStatus.codeOf(project.getProjectStatus()).getName());


        if(order.getOrderType().equals(OrderEnum.OrderType.APPOINTMENTAMOUNT.getValue())) {
            // 预约订单
            HavePayYuYueEntityPO entity=new HavePayYuYueEntityPO();
            BeanUtils.copyProperties(order,entity);
            if(order.getOrderStatus().equals(OrderEnum.PayStatus.PAY_STATUS_OK.getValue())){
                entity.setAllPriceReal(order.getAllPrice());
            }
            resDto.setYuYueEntity(entity);
        }
        if(order.getOrderType().equals(OrderEnum.OrderType.APPOINTMENTAMOUNT_BUY.getValue())) {
            // 预约认购订单
            HavePayAppEntityPO entity=new HavePayAppEntityPO();
            if(StringUtils.isNotEmpty(order.getOldcode())){
                OrderEntityOutDTO oldOrder=this.orderService.findEntityByOrderSn(order.getOldcode());
                entity.setPrePrice(oldOrder.getInvestMoney());
                entity.setPreDingJinPriceShould(oldOrder.getAllPrice());
                if(order.getPayStatus().equals(OrderEnum.PayStatus.PAY_STATUS_OK.getValue())){
                    entity.setPreDingJinPriceReal(oldOrder.getAllPrice());
                }
                entity.setPrePayName(oldOrder.getPayName());
                entity.setPreAddTime(oldOrder.getAddTime());
                entity.setPrePayTime(oldOrder.getPayTime());
            }else{
                resDto.exception("预约认购订单["+order.getOrderSn()+"]信息有误，找不到原始预约单");
                return resDto;
            }
            entity.setRgInvestMoney(order.getInvestMoney());
            entity.setRgYuyuePrice(order.getYuyuePrice());
            entity.setRgSharePrice(order.getShareAll());
            entity.setRgMoneyShould(order.getAllPrice());
            if(order.getPayStatus().equals(OrderEnum.PayStatus.PAY_STATUS_OK.getValue())){
                entity.setRgMoneyReal(order.getAllPrice());
            }
            entity.setRgPayName(order.getPayName());
            entity.setRgAddTime(order.getAddTime());
            entity.setRgPayTime(order.getPayTime());

            resDto.setAppEntity(entity);
        }
        if(order.getOrderType().equals(OrderEnum.OrderType.OPEN_BUY.getValue())) {
            // 公开认购订单
            HavePayRenGouEntityPO entity=new HavePayRenGouEntityPO();
            BeanUtils.copyProperties(order,entity);
            entity.setAllPriceShould(order.getAllPrice());
            if(order.getPayStatus().equals(OrderEnum.PayStatus.PAY_STATUS_OK.getValue())){
                entity.setAllPriceReal(order.getAllPrice());
            }
            resDto.setRenGouEntity(entity);
        }
        resDto.success();
        return resDto;
    }


    @Override
    public HasProjectOutputDTO findHasProjectPage(HasProjectInputDTO dto) {
        HasProjectOutputDTO response = new HasProjectOutputDTO();
        HasProjectParamPO paramPO=new HasProjectParamPO();
        BeanUtils.copyProperties(dto,paramPO);
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        //1-筹资中（"05,06,07,08,09,12"）
        if("1".equals(dto.getProjectFlag())){
            paramPO.setProjectStatusArr(ProjectEnum.ProjectStatus.APPOINTMENT.getValue()+","
                            +ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue()+","
                            +ProjectEnum.ProjectStatus.SUBSCRIBE.getValue()+","
                            +ProjectEnum.ProjectStatus.CALMNESS_PERIOD.getValue()+","
                            +ProjectEnum.ProjectStatus.DELAY.getValue()+","
                            +ProjectEnum.ProjectStatus.SUCCESS.getValue());
        }
        // 2-分红中（"13"）
        if("2".equals(dto.getProjectFlag())){
            paramPO.setProjectStatusArr(ProjectEnum.ProjectStatus.IN_RED.getValue());
        }
        // 3-已完成（"14"）
        if("3".equals(dto.getProjectFlag())){
            paramPO.setProjectStatusArr(ProjectEnum.ProjectStatus.FINISH.getValue());
        }
        // 4-失败（"10"）
        if("4".equals(dto.getProjectFlag())){
            paramPO.setProjectStatusArr(ProjectEnum.ProjectStatus.FAIL.getValue());
        }
        paramPO.setList(StringUtils.strToList(paramPO.getProjectStatusArr()));
        List<HasProjectPO>  hpList= this.baseMapper.findHasProjectPage(paramPO);
        PageInfo pageInfo=new PageInfo(hpList);
        if(hpList !=null && hpList.size()>0){
            response.setPageInfo(pageInfo);
            response.setList(hpList);
            hpList.forEach((HasProjectPO temp)->{
                List<HasPlanPO>  planList=this.findPlanListByUpId(dto.getUserId(),temp.getProjectId(),false);
                temp.setPlanList(planList);
            });
            return response;
        }else{
            return null;
        }
    }


    @Override
    public DetailHasProjectOutputDTO detailHasProject(DetailHasProjectInputDTO dto){
        DetailHasProjectOutputDTO resDto=new DetailHasProjectOutputDTO();

        ProjectOneDetailVO project=this.productClient.findDetailByProjectId(dto.getProjectId()+"");
        resDto.setSuccessTime(project.getSuccessDate());//项目“筹资成功时间”
        resDto.setProjectId(dto.getProjectId());
        resDto.setImageUrl(project.getAppImageUrl());
        resDto.setProjectName(project.getProjectName());
        resDto.setProjectStatus(project.getProjectStatus());
        resDto.setProjectStatusName(ProjectEnum.ProjectStatus.codeOf(project.getProjectStatus()).getName());

        InputDictionaryQueryDTO query = new InputDictionaryQueryDTO();
        query.setType("project_type");
        query.setValue(project.getProjectType());
        OutputDictionaryDetailDTO outputDictionaryDetailDTO =  this.basicsClient.findLabelByValueAndType(query);
        //小楼图片
        resDto.setLogoUrl(outputDictionaryDetailDTO==null?"":outputDictionaryDetailDTO.getIcon());
        //小楼名称-酒店
        resDto.setLogoName(outputDictionaryDetailDTO==null?"":outputDictionaryDetailDTO.getLabel());
        //股权
        InputDictionaryQueryDTO rightsQuery = new InputDictionaryQueryDTO();
        rightsQuery.setType("rights_type");
        rightsQuery.setValue(project.getProjectType());
        OutputDictionaryDetailDTO rightsDicDTO =  this.basicsClient.findLabelByValueAndType(rightsQuery);
        resDto.setStockName(rightsDicDTO==null?"":rightsDicDTO.getLabel());

        //投资方案明细列表
        List<HasPlanPO>  planList=this.findPlanListByUpId(dto.getUserId(),dto.getProjectId(),true);
        resDto.setPlanList(planList);
        BigDecimal investReal = planList.stream().map(p->p.getInvestMoney()).reduce(BigDecimal.ZERO, BigDecimal::add);
        resDto.setInvestReal(investReal);

        //分红列表
        List<HasRedPO> redList=new ArrayList<HasRedPO>();
        FindFoumulListInputDTO forMDto=new FindFoumulListInputDTO();
        forMDto.setInvestId(dto.getUserId());
        forMDto.setProductId(dto.getProjectId());
        List<ProjectFormula> fList=this.projectFormulaService.findList(forMDto);
        if(fList!=null && fList.size()>0){
            fList.forEach((ProjectFormula temp)->{
                HasRedPO red=new HasRedPO();
                red.setAmountLeiji(temp.getAmountLeiji());
                red.setBenqiRealRedAmount(temp.getBenqiRealRedAmount());
                red.setRealShareTime(temp.getRealShareTime());
                redList.add(red);
            });
            resDto.setRedList(redList);
        }else{
            log.info("【提示：用户（"+dto.getUserId()+"）购买的项目（"+dto.getProjectId()+"）暂无分红记录信息】");
        }
        return resDto;
    }

    //根据用户和项目id查询方案列表
    public List<HasPlanPO> findPlanListByUpId(Long userId,Long projectId,Boolean ifShowChilden){
        //一级
        Map<String,Object> paramG=new HashMap<String,Object>();
        paramG.put("userId",userId);
        paramG.put("projectId",projectId);
        List<HasPlanPO>  suList=this.orderProductService.listSumPlan(paramG);
        if(suList==null || suList.size()==0){
            log.info("detailHasProject查询失败，用户（"+userId+"）没有购买项目（"+projectId+"）的记录");
            return new ArrayList<HasPlanPO>();
        }

        //二级
       if(StringUtils.isNotEmpty(ifShowChilden) && ifShowChilden.equals(true)){
           suList.forEach((HasPlanPO planEn)->{
                List<HasPlanPO> opChildenList=new ArrayList<HasPlanPO>();
                List<OrderProduct>  opList= this.orderProductService.findOpListByProjectId(userId,projectId);
                if(opList!=null && opList.size()>0){
                    opList.forEach((OrderProduct temp)->{
                        HasPlanPO chil=new HasPlanPO();
                        chil.setPlanName(temp.getProductPlan());
                        chil.setPlanId(temp.getPlanId());
                        chil.setInvestMoney(temp.getLessTotal());
                        chil.setBuyNum(temp.getNum());
                        opChildenList.add(chil);
                    });
                    planEn.setChildenList(opChildenList);
                }
            });
        }

        return suList;
    }


    /**
     * “预约认购开始到公开认购结束”这段时间，自动生成预约认购尾款订单
     * `purchase_start_time` timestamp NULL DEFAULT NULL COMMENT '申购开始时间(预约认购开始时间)',
     *  purchase_end_time` timestamp NULL DEFAULT NULL COMMENT '申购结束时间（公开认购结束时间）',
     * @return
     */
   /* public String taskAppointmentRenGouOrder(){
        log.info("【定时任务-“预约认购开始到公开认购结束”这段时间，自动生成预约认购尾款订单,执行开始=========】");
        String code="";

        RenGouListParamPO pramPo=new RenGouListParamPO();
        pramPo.setOrderStauts(OrderEnum.OrderStataus.ORDER_STATUS_NORMAL.getValue());
        pramPo.setPayStatus(OrderEnum.PayStatus.PAY_STATUS_OK.getValue());
        pramPo.setOrderType(OrderEnum.OrderType.APPOINTMENTAMOUNT.getValue());
        //(预约认购+公开认购)06,07
        String projectStatusArr=ProjectEnum.ProjectStatus.APPOINTMENT_SUBSCRIBE.getValue()+","
                +ProjectEnum.ProjectStatus.SUBSCRIBE.getValue();
        pramPo.setList(StringUtils.strToList(projectStatusArr));
        pramPo.setPurchaseCurrentTime(DateUtils.formatDate(new Date(),DateUtils.DATE_FROMAT2));
        pramPo.setOldcodeIsNull("isNull");
        List<Order>  orderList=this.baseMapper.findRenGouQiOrderList(pramPo);
        if(orderList==null || orderList.size()==0){
            return "暂无预约认购的订单";
        }

        //对于预约款订单，自动生成尾款订单
        orderList.forEach((Order temp)->{
            Order weiKuanOrder=new Order();
            BeanUtils.copyProperties(temp,weiKuanOrder);
            weiKuanOrder.setOrderType(OrderEnum.OrderType.APPOINTMENTAMOUNT_BUY.getValue());
            weiKuanOrder.setOrderId(null);
            String orderSnNew=this.basicsClient.getOrderJournalSn();
            weiKuanOrder.setOrderSn(orderSnNew);
            weiKuanOrder.setOldcode(temp.getOrderSn());//尾款订单，挂预约订单号
            weiKuanOrder.setAddTime(new Date());
            weiKuanOrder.setOrderStatus(OrderEnum.OrderStataus.ORDER_STATUS_NORMAL.getValue());
            weiKuanOrder.setPayStatus(OrderEnum.PayStatus.PAY_STATUS_NO.getValue());
            //金额
            BigDecimal weiKuan=temp.getInvestMoney().subtract(temp.getAllPrice());
            weiKuanOrder.setOrgPrice(weiKuan);//投资金额-预约款=剩余尾款
            weiKuanOrder.setYuyuePrice(temp.getAllPrice());
            //weiKuanOrder.setShareAll();
            weiKuanOrder.setAllPrice(weiKuan);
            this.baseMapper.insert(weiKuanOrder);
        });





        // 短信通知用户
        log.info("【定时任务-“预约认购开始到公开认购结束”这段时间，自动生成预约认购尾款订单,执行结束=========】");
        return code;
    }*/
}
