package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.AdminRoleFun;
import org.fh.general.ecom.basics.dao.AdminRoleFunDao;
import org.fh.general.ecom.basics.service.AdminRoleFunService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 管理员角色菜单表 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Service
public class AdminRoleFunServiceImpl extends ServiceImpl<AdminRoleFunDao, AdminRoleFun> implements AdminRoleFunService {

    @Override
    public String deleteByRoleId(Long roleId) throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("roleId",roleId);
        this.baseMapper.deleteByMap(map);
        return OutEnum.SUCCESS.getCode();
    }
	
}
