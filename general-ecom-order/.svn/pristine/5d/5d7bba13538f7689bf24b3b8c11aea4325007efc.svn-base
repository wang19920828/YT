package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;
import org.fh.general.ecom.order.model.OrderLog;
import org.fh.general.ecom.order.dao.OrderLogDao;
import org.fh.general.ecom.order.service.OrderLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单操作日志 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@Slf4j
@Service
public class OrderLogServiceImpl extends ServiceImpl<OrderLogDao, OrderLog> implements OrderLogService {

    @Override
    public OrderLogListOutputDTO findPage(OrderLogListInputDTO dto)throws Exception {
        OrderLogListOutputDTO response=new OrderLogListOutputDTO();
        /*EntityWrapper<OrderLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        *//* if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*//*
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<OrderLog> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<OrderLogListOutPO>  listpo=new ArrayList<OrderLogListOutPO>();
        list.forEach((OrderLog temp) -> {
            OrderLogListOutPO po=new OrderLogListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);*/
        return response;
    }


        @Override
        public String addEntity(OrderLogAddInputDTO dto)  throws Exception{
            String code="";
            try {
                OrderLog entity=new OrderLog();
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
    public String deleteEntityById(Long id) {
        OrderLog entity=new OrderLog();
        entity.setId(id);
        //entity.setIsDelete(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(OrderLogUpdateInputDTO dto) {
        OrderLog entity=new OrderLog();
        BeanUtils.copyProperties(dto,entity);
        OrderLog param=new OrderLog();
        param.setId(dto.getId());
        OrderLog findOne= baseMapper.selectOne(param);
        if(findOne==null){
            return OutEnum.WARN.getCode();
        }
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public OrderLogDetailOutputDTO findEntityById(Long id) {
        OrderLogDetailOutputDTO response=new OrderLogDetailOutputDTO();
        OrderLog entity=baseMapper.selectById(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response );
            return response;
        }
        return null;
    }


    /**
     * 根据订单号查询操作日志列表(不分页)
     * @param orderSn
     * @return
     */
    @Override
    public OrderLogListOutputDTO findListByOrderSn(String orderSn) {
        OrderLogListOutputDTO dto=new OrderLogListOutputDTO();
        EntityWrapper<OrderLog> wrapper = new EntityWrapper<>();
        //PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        wrapper.eq("order_sn",orderSn);
        List<OrderLog> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);

        if(list !=null && list.size()>0){
            List<OrderLogListOutPO> poList=new ArrayList<OrderLogListOutPO>();
            list.forEach((OrderLog temp)->{
                OrderLogListOutPO poEn=new OrderLogListOutPO();
                BeanUtils.copyProperties(temp,poEn);
                poList.add(poEn);
            });
            dto.setList(poList);
            //dto.setPageInfo(pageInfo);
        }
        return dto;
    }


}
