package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowEntityInputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowEntityOutputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListInputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListOutputDTO;
import org.fh.general.ecom.common.po.order.dealFlow.DealFlowListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.order.dao.DealFlowDao;
import org.fh.general.ecom.order.model.DealFlow;
import org.fh.general.ecom.order.service.DealFlowService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 交易流水 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@Slf4j
@Service
public class DealFlowServiceImpl extends ServiceImpl<DealFlowDao, DealFlow> implements DealFlowService {


    @Override
    public DealFlowListOutputDTO findPage(DealFlowListInputDTO dto) {
        DealFlowListOutputDTO response=new DealFlowListOutputDTO();
        EntityWrapper<DealFlow> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );

        if(StringUtils.isNotEmpty(dto.getFlowNo())){
            wrapper.eq("flow_no",dto.getFlowNo());
        }
        if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }
        if(StringUtils.isNotEmpty(dto.getTransType())){
            wrapper.eq("trans_type",dto.getTransType());
        }
        if(StringUtils.isNotEmpty(dto.getAddTimeStart()) || StringUtils.isNotEmpty(dto.getAddTimeEnd())){
            wrapper.between("add_time",dto.getAddTimeStart(),dto.getAddTimeEnd());
        }
        if(StringUtils.isNotEmpty(dto.getPayType())){
            wrapper.eq("pay_type",dto.getPayType());
        }

        log.info("================="+wrapper.getSqlSegment());
        List<DealFlow> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<DealFlowListOutPO>  listpo=new ArrayList<DealFlowListOutPO>();
        list.forEach((DealFlow temp) -> {
            DealFlowListOutPO po=new DealFlowListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public DealFlowEntityOutputDTO  findEntityByParams(DealFlowEntityInputDTO dto){
        DealFlowEntityOutputDTO response=new DealFlowEntityOutputDTO();
        DealFlow df=new DealFlow();
        BeanUtils.copyProperties(dto,df);
        this.baseMapper.selectOne(df);
        return response;
    }
}
