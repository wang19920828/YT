package org.fh.general.ecom.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.user.electronicsAccount.ElectronicsAccountRechargePayInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.FindFoumulListInputDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperAddDTO;
import org.fh.general.ecom.common.enumeration.order.FormulaEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.order.client.BasicsClient;
import org.fh.general.ecom.order.client.ProductClient;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Component
public class TimedTaskShareRed {

    @Autowired
    private ProjectFormulaService projectFormulaService;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private BasicsClient basicsClient;


    /**
     * 定时任务每天凌晨7点，做分红
     * //@Scheduled(fixedRate=60000) //每分钟执行一次
     * @return
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public BaseVO executeShareRed(){
        log.info("【=========定时任务-按照分红试算计划开始分红 开始=========】");
        BaseVO baseVo=new BaseVO();
        try {
            FindFoumulListInputDTO forDto=new FindFoumulListInputDTO();
            forDto.setShareStatus("1");//状态：1-待执行分红 2-分红执行成功 3-分红执行失败 4-逾期'
            forDto.setAuditStatus("2");//审核状态：1-待审核 2-通过 3-驳回
            Date  dd= DateUtils.getDate(DateUtils.getLastDate(System.currentTimeMillis()));//今天23:59:59
            forDto.setPreShareTimeEnd(dd);
            List<ProjectFormula> formulList=this.projectFormulaService.findList(forDto);
            if(formulList==null || formulList.size()==0){
                String msg="没有查询到可分红的计划。即tb_red_project_formula表需有数据满足：pre_share_time<=今天；分红状态=待执行（exp1=1）；审核状态=通过（exp2=2）";
                log.info(msg);
                baseVo.noData(msg);
                return baseVo;
            }

            StringBuffer sb=new StringBuffer();
            formulList.forEach((ProjectFormula temp)->{
                String accountNo=temp.getAccountNo();
                // 往用户账号存钱
                ElectronicsAccountRechargePayInputDTO  accountDto=new ElectronicsAccountRechargePayInputDTO();
                accountDto.setOrderSn(temp.getOrderSn());
                accountDto.setTransAmt(temp.getBenqiRealRedAmount());
                BaseVO baseAccountVO=this.basicsClient.addMoneyToAccount(accountDto);
                //存operaLog
                InputProjectOperAddDTO  operEn=new InputProjectOperAddDTO();
                if(!OutEnum.SUCCESS.getCode().equals(baseAccountVO.getMsg().getCode())){
                    temp.setExp1("3");
                    temp.setRemark("分红失败，往账户发放分红金额失败！");
                    operEn.setRedStatus("4");
                    operEn.setRedBeOverdue("2");//逾期
                    sb.append("tb_red_project_formula表id="+temp.getId()+"分红失败。");
                }else{
                    temp.setExp1("2");
                    temp.setRemark("分红成功！");
                    //operEn.setRedStatus(this.transformShareRedStatus(temp.getExp1(),"2"));//分红状态：1-待申请/2-待审核/3-驳回/4-待分红/5-已分红
                    operEn.setRedStatus("5");
                    operEn.setRedBeOverdue("1");//否逾期
                }
                operEn.setProjectId(temp.getProductId());
                operEn.setOperTime(DateUtils.formatDate(new Date(),DateUtils.DATE_FROMAT2));
                operEn.setRedNum(temp.getNumbers());
                operEn.setRedTotalAmount(temp.getAmountLeiji());
                operEn.setRedThisAmount(temp.getBenqiPreRedAmount());
                operEn.setRedActualAmount(temp.getBenqiRealRedAmount());
                operEn.setRedTotalNum(temp.getAllNumbers());//分红总次数
                this.productClient.insertProjectOperLog(operEn);

                temp.setRealShareTime(new Date());
                this.projectFormulaService.updateAllColumnById(temp);
            });
            if(sb.length()==0){
                baseVo.success();
            }else{
                baseVo.error(sb.toString());
            }
        }catch (Exception e) {
            baseVo.exception();
            e.printStackTrace();
        } finally {
            log.info("【=========定时任务-按照分红试算计划开始分红 结束========="+baseVo.getMsg()+"】");
        }
        return baseVo;
    }



    //分红状态，转换成operLog里面的状态
 /*   public String transformShareRedStatus(String shareStatus,String auditStatus){//formulStatus状态：1-待执行分红 2-分红执行成功 3-分红执行失败 4-逾期
        String operLogStatus="1";//1-待申请/2-待审核/3-驳回/4-待分红/5-已分红
        if(auditStatus.equals(FormulaEnum.AuditStatus.AUDIT_STATUS_WAIT.getValue())){
            operLogStatus="2";
        }else if(auditStatus.equals(FormulaEnum.AuditStatus.AUDIT_STATUS_SUCCESS.getValue())){
            operLogStatus="4";
            if(shareStatus.equals(FormulaEnum.ShareRedStatus.RED_STATUS_SUCCESS.getValue())){
                operLogStatus="5";
            }
        }else if(auditStatus.equals(FormulaEnum.AuditStatus.AUDIT_STATUS_FAIL.getValue())){
            operLogStatus="3";
        }

        return operLogStatus;
    }*/



}
