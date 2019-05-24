package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.model.AdminLog;
import org.fh.general.ecom.basics.dao.AdminLogDao;
import org.fh.general.ecom.basics.model.AdminLoginLog;
import org.fh.general.ecom.basics.service.AdminLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListOutputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminLog.AdminLogListOutputPO;
import org.fh.general.ecom.common.po.basics.adminLoginLog.AdminLoginLogListOutputPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 管理员操作日志 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogDao, AdminLog> implements AdminLogService {

    @Override
    public AdminLogListOutputDTO findPage(AdminLogListInputDTO dto) throws Exception {
        AdminLogListOutputDTO response = new AdminLogListOutputDTO();
        EntityWrapper<AdminLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        System.out.println("================where条件:" + wrapper.getSqlSegment());
        List<AdminLog> list = baseMapper.selectList(wrapper);

        PageInfo pageInfo = new PageInfo(list);
        List<AdminLogListOutputPO> listpo = new ArrayList<AdminLogListOutputPO>();
        list.forEach((AdminLog temp) -> {
            AdminLogListOutputPO po = new AdminLogListOutputPO();
            BeanUtils.copyProperties(temp, po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(AdminLogAddInputDTO dto) throws Exception {
        String code = "";
        try {
            AdminLog entity = new AdminLog();
            BeanUtils.copyProperties(dto, entity);
            entity.setOperTime(new Date());
            baseMapper.insert(entity);
            code = OutEnum.SUCCESS.getCode();
        } catch (Exception e) {
            e.printStackTrace();
            code = OutEnum.FAIL.getCode();
        }
        return code;
    }
	
}
