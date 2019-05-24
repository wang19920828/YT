package org.fh.general.ecom.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.fh.general.ecom.common.dto.product.organization.InputOrganizationUpdateDTO;
import org.fh.general.ecom.common.po.product.organization.OrganizationLogOutputPO;
import org.fh.general.ecom.product.dao.OrganizationLogDao;
import org.fh.general.ecom.product.model.OrganizationLog;
import org.fh.general.ecom.product.service.OrganizationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
@Service
@Transactional
public class OrganizationLogServiceImpl extends ServiceImpl<OrganizationLogDao, OrganizationLog> implements OrganizationLogService {

    @Override
    public void insertEntity(InputOrganizationUpdateDTO dto) {
        OrganizationLog log = new OrganizationLog();
        log.setCompanyId(dto.getId());
        log.setUpdateBy(dto.getUpdateBy());
        log.setUpdateDate(new Date());
        log.setRemarks(dto.getRemarks());
        log.setUpdateName(dto.getUpdateName());
        this.baseMapper.insert(log);

    }

    @Override
    public List<OrganizationLogOutputPO> findList(String id) {
        List<OrganizationLogOutputPO> listPo = new ArrayList<OrganizationLogOutputPO>();
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("company_id",id);
        List<OrganizationLog>  logs =this.baseMapper.selectList(wrapper);
        logs.forEach((OrganizationLog temp) -> {
            OrganizationLogOutputPO po=new OrganizationLogOutputPO();
            BeanUtils.copyProperties(temp,po);
            listPo.add(po);
        });

        return listPo;
    }
}
