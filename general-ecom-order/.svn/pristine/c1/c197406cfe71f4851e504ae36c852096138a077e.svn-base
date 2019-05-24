package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogUpdateInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.requestLog.RequestLogListOutPO;
import org.fh.general.ecom.order.model.RequestLog;
import org.fh.general.ecom.order.dao.RequestLogDao;
import org.fh.general.ecom.order.service.RequestLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 请求日志表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@Slf4j
@Service
public class RequestLogServiceImpl extends ServiceImpl<RequestLogDao, RequestLog> implements RequestLogService {


    @Override
    public RequestLogListOutputDTO findPage(RequestLogListInputDTO dto)throws Exception {
        RequestLogListOutputDTO response=new RequestLogListOutputDTO();
        EntityWrapper<RequestLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /* if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<RequestLog> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<RequestLogListOutPO>  listpo=new ArrayList<RequestLogListOutPO>();
        list.forEach((RequestLog temp) -> {
            RequestLogListOutPO po=new RequestLogListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(RequestLogAddInputDTO dto)  throws Exception{
        String code="";
        try {
            RequestLog entity=new RequestLog();
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
        RequestLog entity=new RequestLog();
        entity.setAutoId(id);
        //entity.setIsDelete(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(RequestLogUpdateInputDTO dto) {
        RequestLog entity=new RequestLog();
        BeanUtils.copyProperties(dto,entity);
        RequestLog param=new RequestLog();
        param.setAutoId(dto.getId());
        RequestLog findOne= baseMapper.selectOne(param);
        if(findOne==null){
            return OutEnum.WARN.getCode();
        }
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public RequestLogDetailOutputDTO findEntityById(Long id) {
        RequestLogDetailOutputDTO response=new RequestLogDetailOutputDTO();
        RequestLog entity=baseMapper.selectById(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response );
            return response;
        }
        return null;
    }

}
