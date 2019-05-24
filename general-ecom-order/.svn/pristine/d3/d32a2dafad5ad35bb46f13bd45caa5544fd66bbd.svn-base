package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListInputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListOutputDTO;
import org.fh.general.ecom.common.po.order.dealFlow.DealFlowListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.order.dealFlow.DealFlowListVO;
import org.fh.general.ecom.order.service.DealFlowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 交易流水 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@RestController
public class DealFlowController {

    @Autowired
    private DealFlowService dealFlowService;



    /**
     * 分页列表
     * */
    @RequestMapping("DF8005")
    public PagingVO findPage(DealFlowListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        DealFlowListOutputDTO dtoEntity= this.dealFlowService.findPage(dto);
        List<DealFlowListOutPO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        List<DealFlowListVO> listvo=new ArrayList<DealFlowListVO>();
        list.forEach((DealFlowListOutPO temp) -> {
            DealFlowListVO voEn=new DealFlowListVO();
            BeanUtils.copyProperties(temp,voEn);
            listvo.add(voEn);
        });

        pagingVO.success(listvo,dtoEntity.getPageInfo() );
        return  pagingVO;
    }
    
    
}
