package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.model.AdminLoginLog;
import org.fh.general.ecom.basics.dao.AdminLoginLogDao;
import org.fh.general.ecom.basics.service.AdminLoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminLoginLog.AdminLoginLogListOutputPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 管理员登录日志 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogDao, AdminLoginLog> implements AdminLoginLogService {

    @Override
    public AdminLoginLogListOutputDTO findPage(AdminLoginLogListInputDTO dto) throws Exception {
        AdminLoginLogListOutputDTO response = new AdminLoginLogListOutputDTO();
        EntityWrapper<AdminLoginLog> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        /*
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        System.out.println("================where条件:" + wrapper.getSqlSegment());
        List<AdminLoginLog> list = baseMapper.selectList(wrapper);

        PageInfo pageInfo = new PageInfo(list);
        List<AdminLoginLogListOutputPO> listpo = new ArrayList<AdminLoginLogListOutputPO>();
        list.forEach((AdminLoginLog temp) -> {
            AdminLoginLogListOutputPO po = new AdminLoginLogListOutputPO();
            BeanUtils.copyProperties(temp, po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }


    @Override
    public String addEntity(AdminLoginLogAddInputDTO dto) throws Exception {
        String code = "";
        try {
            AdminLoginLog entity = new AdminLoginLog();
            BeanUtils.copyProperties(dto, entity);
            entity.setLoginTime(new Date());
            baseMapper.insert(entity);
            code = OutEnum.SUCCESS.getCode();
        } catch (Exception e) {
            e.printStackTrace();
            code = OutEnum.FAIL.getCode();
        }
        return code;
    }

}
