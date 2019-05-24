package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.order.projectFormula.FindFoumulListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.JiSuanShareRedAmountInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaAddInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListInputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.ProjectFormulaListOutputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.SumOnePlanAmountInputDTO;
import org.fh.general.ecom.common.enumeration.order.OrderEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.po.order.projectFormula.ProjectFormulaListOutPO;
import org.fh.general.ecom.common.po.order.projectFormula.SumFormulaByPlanIdPO;
import org.fh.general.ecom.common.po.product.order.OrderCountListOutPO;
import org.fh.general.ecom.order.model.ProjectFormula;
import org.fh.general.ecom.order.dao.ProjectFormulaDao;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分红试算/日历详情表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Service
public class ProjectFormulaServiceImpl extends ServiceImpl<ProjectFormulaDao, ProjectFormula> implements ProjectFormulaService {



    @Override
    public ProjectFormulaListOutputDTO findPage(ProjectFormulaListInputDTO dto)throws Exception {
        ProjectFormulaListOutputDTO response=new ProjectFormulaListOutputDTO();
        EntityWrapper<ProjectFormula> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        List<ProjectFormula> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<ProjectFormulaListOutPO>  listpo=new ArrayList<ProjectFormulaListOutPO>();
        list.forEach((ProjectFormula temp) -> {
            ProjectFormulaListOutPO po=new ProjectFormulaListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public  List<ProjectFormula> findList(FindFoumulListInputDTO dto){
        EntityWrapper<ProjectFormula> wrapper = new EntityWrapper<ProjectFormula>();
        if(StringUtils.isNotEmpty(dto.getShareStatus())){
            wrapper.eq("exp1",dto.getShareStatus());
        }
        if(StringUtils.isNotEmpty(dto.getAuditStatus())){
            wrapper.eq("exp2",dto.getAuditStatus());
        }
        if(StringUtils.isNotEmpty(dto.getPreShareTimeEnd())){
            wrapper.lt("pre_share_time",dto.getPreShareTimeEnd());//LT是小于号
        }
        if(StringUtils.isNotEmpty(dto.getInvestId())){
            wrapper.eq("invest_id",dto.getInvestId());
        }
        if(StringUtils.isNotEmpty(dto.getProductId())){
            wrapper.eq("product_id",dto.getProductId());
        }
        List<ProjectFormula> list=baseMapper.selectList(wrapper);
        return list;

    }


    @Override
    public String addEntity(ProjectFormulaAddInputDTO dto)  throws Exception{
        String code="";
        try {
            ProjectFormula entity=new ProjectFormula();
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
    public SumFormulaByPlanIdPO sumFormulaByPlanId(String planId){

        return this.baseMapper.sumFormulaByPlanId(planId);
    }


    //本期分红金额（元）=订单方案的投资金额（元）*当前年化收益率/12（月）*分红周期（月）
    @Override
    public BigDecimal jiSuanShareRedAmount(JiSuanShareRedAmountInputDTO dto){
        BigDecimal allPrice=dto.getAllPrice();
        BigDecimal yearRate=dto.getYearRate();
        BigDecimal cycle=dto.getCycle();
        BigDecimal rate=yearRate.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);//收益率
        BigDecimal eveMonth=rate.divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);//每月收益率
        BigDecimal amount = allPrice.multiply(eveMonth).multiply(cycle).setScale(BigDecimal.ROUND_HALF_UP,2);
        return amount;
    }

    /**
     * 统计某一个方案的总分红金额
     * @return
     */
    @Override
    public BigDecimal sumOnePlanAmount(SumOnePlanAmountInputDTO dto){

        return this.baseMapper.sumOnePlanAmount(dto);
    }


   public BigDecimal findEntity(OrderCountListOutPO dto){
        ProjectFormula projectFormula=new ProjectFormula();
        projectFormula.setRedProjectId(Long.valueOf(dto.getProjectId()));
        projectFormula.setInvestId(Long.valueOf(dto.getUserId()));
        projectFormula.setExp1("2");
        projectFormula = baseMapper.sumAmount(projectFormula);
        if(projectFormula!=null){
           return projectFormula.getAmountInvest();
        }
        return BigDecimal.ZERO;
    }



}
