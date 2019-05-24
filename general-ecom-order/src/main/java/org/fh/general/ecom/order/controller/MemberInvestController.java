package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.memberInvest.InvestShareLogInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.InvestShareLogOutputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberDetailPageInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberDetailPageOutputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestAddInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestListInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestListOutputDTO;
import org.fh.general.ecom.common.dto.order.projectFormula.SumOnePlanAmountInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.enums.ProjectEnum;
import org.fh.general.ecom.common.po.order.memberInvest.MemberInvestListOutPO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberInvestListVO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberInvestLogVO;
import org.fh.general.ecom.order.service.MemberInvestService;
import org.fh.general.ecom.order.service.ProjectFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员投资表（一个会员多次购买同一个项目的不同方案）；以项目为单位 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class MemberInvestController {



    @Autowired
    private MemberInvestService memberInvestService;
    @Autowired
    private ProjectFormulaService projectFormulaService;

    /**
     * 分页列表
     * */
    @RequestMapping("MEM8005")
    public PagingVO findPage(MemberInvestListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            MemberInvestListOutputDTO dtoEntity= this.memberInvestService.findPage(dto);
            List<MemberInvestListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<MemberInvestListVO> listvo=new ArrayList<MemberInvestListVO>();
            list.forEach((MemberInvestListOutPO temp) -> {
                MemberInvestListVO voEn=new MemberInvestListVO();
                BeanUtils.copyProperties(temp,voEn);

                SumOnePlanAmountInputDTO sumDto=new SumOnePlanAmountInputDTO();
                BigDecimal leiji=this.projectFormulaService.sumOnePlanAmount(sumDto);
                voEn.setLeijiShareAmount(leiji);
                voEn.setProjectStatus(ProjectEnum.ProjectStatus.codeOf(temp.getProjectStatus()).getName());
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }



    /**
     * 添加
     * */
    @RequestMapping("MEM8001")
    public BaseVO addEntity(MemberInvestAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.memberInvestService.addEntity(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }

    /**
     * 会员投资详情（后台）
     * @param paramDto
     * @return
     */
    @RequestMapping("MEM8002")
    public BaseVO detailBgMemberInvest(MemberDetailPageInputDTO paramDto){
        BaseVO baseVO=new BaseVO();
        //DetailMemberPagingVO pagingVO=new DetailMemberPagingVO();
        MemberDetailPageOutputDTO dtoEntity=this.memberInvestService.detailBgMemberInvest(paramDto);
        if(dtoEntity==null){
            baseVO.noData();
            return baseVO;
        }

        baseVO.success(dtoEntity);
        return  baseVO;
    }

    /**
     * 投资详情- 投资分红记录分页
     * */
    @RequestMapping("MEM8003")
    public PagingVO detailInvestShareLogPage(InvestShareLogInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        InvestShareLogOutputDTO dtoEntity= this.memberInvestService.detailInvestShareLogPage(dto);
        List<MemberInvestLogVO> list= dtoEntity.getLogList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo() );
        return  pagingVO;
    }




}
