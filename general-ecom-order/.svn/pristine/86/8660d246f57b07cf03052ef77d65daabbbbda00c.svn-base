package org.fh.general.ecom.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.auditLog.AuditLogListOutPO;
import org.fh.general.ecom.order.model.AuditLog;
import org.fh.general.ecom.order.dao.AuditLogDao;
import org.fh.general.ecom.order.model.AuditLog;
import org.fh.general.ecom.order.service.AuditLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分红审核日志表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Service
public class AuditLogServiceImpl extends ServiceImpl<AuditLogDao, AuditLog> implements AuditLogService {

    @Override
    public AuditLogListOutputDTO findPage(AuditLogListInputDTO dto)throws Exception {
        AuditLogListOutputDTO response=new AuditLogListOutputDTO();
        EntityWrapper<AuditLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        List<AuditLog> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<AuditLogListOutPO>  listpo=new ArrayList<AuditLogListOutPO>();
        list.forEach((AuditLog temp) -> {
            AuditLogListOutPO po=new AuditLogListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(AuditLogAddInputDTO dto)  throws Exception{
        String code="";
        try {
            AuditLog entity=new AuditLog();
            BeanUtils.copyProperties(dto,entity );
            baseMapper.insert(entity);
            code= OutEnum.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            code=OutEnum.FAIL.getCode();
        }
        return code;
    }
}
