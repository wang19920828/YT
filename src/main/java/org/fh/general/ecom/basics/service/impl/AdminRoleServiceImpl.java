package org.fh.general.ecom.basics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.fh.general.ecom.basics.model.AdminFunction;
import org.fh.general.ecom.basics.model.AdminRole;
import org.fh.general.ecom.basics.dao.AdminRoleDao;
import org.fh.general.ecom.basics.model.AdminRoleFun;
import org.fh.general.ecom.basics.service.AdminFunctionService;
import org.fh.general.ecom.basics.service.AdminRoleFunService;
import org.fh.general.ecom.basics.service.AdminRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.adminRole.*;
import org.fh.general.ecom.common.enumeration.basics.MongoEnum;
import org.fh.general.ecom.common.enumeration.basics.RedisEnum;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.mongo.MongoDbUtil;
import org.fh.general.ecom.common.po.basics.adminRole.RoleListOutputPO;
import org.fh.general.ecom.common.redis.RedisHashService;
import org.fh.general.ecom.common.redis.RedisHashServiceImpl;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleDao, AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private AdminRoleFunService adminRoleFunService;

    @Autowired
    private AdminFunctionService adminFunctionService;

    private RedisHashService redisHashService = new RedisHashServiceImpl();

    @Override
    public RoleListOutputDTO findPage(RoleListInputDTO dto) throws Exception {
        RoleListOutputDTO response = new RoleListOutputDTO();
        EntityWrapper<AdminRole> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        /*if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        System.out.println("================where条件:" + wrapper.getSqlSegment());
        List<AdminRole> list = baseMapper.selectList(wrapper);

        PageInfo pageInfo = new PageInfo(list);
        List<RoleListOutputPO> listpo = new ArrayList<RoleListOutputPO>();
        list.forEach((AdminRole temp) -> {
            RoleListOutputPO po = new RoleListOutputPO();
            BeanUtils.copyProperties(temp, po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

    @Override
    public String addEntity(RoleAddInputDTO dto) throws Exception {
        String code = "";
        try {
            AdminRole entity = new AdminRole();
            entity.setIsDel(ComEnum.IsDelete.NORMAL.getValue());
            entity.setIsDisabled(ComEnum.IsDelete.NORMAL.getValue());
            entity.setCreateTime(new Date());
            BeanUtils.copyProperties(dto, entity);
            baseMapper.insert(entity);
            code = OutEnum.SUCCESS.getCode();
        } catch (Exception e) {
            e.printStackTrace();
            code = OutEnum.FAIL.getCode();
        }
        return code;
    }

    @Override
    public String deleteEntityById(Long id) throws Exception {
        AdminRole entity = new AdminRole();
        entity.setId(id);
        entity.setIsDel(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(RoleUpdateInputDTO dto) throws Exception {
        AdminRole entity = new AdminRole();
        BeanUtils.copyProperties(dto, entity);
        AdminRole param = new AdminRole();
        param.setId(dto.getId());
        AdminRole findOne = baseMapper.selectOne(param);
        if (findOne == null) {
            return OutEnum.WARN.getCode();
        }
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public RoleDetailOutputDTO findEntityById(Long id) throws Exception {
        RoleDetailOutputDTO response = new RoleDetailOutputDTO();
        AdminRole entity = baseMapper.selectById(id);
        if (entity != null) {
            BeanUtils.copyProperties(entity, response);
            return response;
        }
        return null;
    }

    @Override
    public String updateStatus(String ids,String isDisabled)throws Exception{
        Map<String,Object> param=new HashMap<>();
        param.put("ids",ids.split(","));
        param.put("isDisabled",isDisabled);
        this.adminRoleDao.updateStatus(param);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String saveShouquan(ShouquanSaveInputDTO dto) throws Exception {
        String code = "";
        Long roleId=dto.getRoleId();
        String functions=dto.getFunctions();
        try {
            //删除当前角色的功能菜单
            this.adminRoleFunService.deleteByRoleId(roleId);
            //角色重新授权
            if(functions!=null){
                List<String> listFunc = StringUtils.strToList(functions);
                if (listFunc != null && listFunc.size() > 0) {
                    listFunc.forEach((String funcCode) -> {
                        AdminRoleFun entity = new AdminRoleFun();
                        entity.setRoleId(roleId);
                        entity.setFunModuleCode(funcCode);
                        entity.setCreateTime(new Date());
                        this.adminRoleFunService.insert(entity);
                    });
                }
            }
            code = OutEnum.SUCCESS.getCode();
            //放到缓存
            List<AdminFunction> hasFunctions = this.adminFunctionService.findRoleFuncList(roleId);
            Map<String, Object> roleFunMap = new HashMap<String, Object>();
            roleFunMap.put("roleId", roleId);
            MongoDbUtil.deleteMany(MongoEnum.MongoKey.MONGODB_SHOUQUAN.getValue(), roleFunMap);
            roleFunMap.put("hasFunctions", JSONArray.fromObject(hasFunctions).toString());
            MongoDbUtil.insertOne(MongoEnum.MongoKey.MONGODB_SHOUQUAN.getValue(), roleFunMap);

            redisHashService.delete(RedisEnum.RedisKey.QUANXIAN + "_" + roleId);
            redisHashService.set(RedisEnum.RedisKey.QUANXIAN + "_" + roleId, JSONObject.toJSONString(hasFunctions), 0);
        } catch (Exception e) {
            e.printStackTrace();
            code = OutEnum.FAIL.getCode();
        }
        return code;
    }

}
