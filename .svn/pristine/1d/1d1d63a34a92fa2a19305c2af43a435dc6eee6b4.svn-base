package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.model.AdminFunction;
import org.fh.general.ecom.basics.dao.AdminFunctionDao;
import org.fh.general.ecom.basics.service.AdminFunctionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.adminFunction.*;
import org.fh.general.ecom.common.dto.basics.adminRole.ShouquanInitOutputDTO;
import org.fh.general.ecom.common.enumeration.basics.FunctionEnum;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminFunction.FunctionListOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 功能菜单 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Service
public class AdminFunctionServiceImpl extends ServiceImpl<AdminFunctionDao, AdminFunction> implements AdminFunctionService {

    @Autowired
    private AdminFunctionDao adminFunctionDao;

    @Override
    public FunctionListOutputDTO findPage(FunctionListInputDTO dto)throws Exception {
        FunctionListOutputDTO response=new FunctionListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        Map<String,Object> map=new HashMap<>();
        if(StringUtils.isNotEmpty(dto.getParentId())){
            map.put("parentId",dto.getParentId());
        }
        List<AdminFunction> list=adminFunctionDao.findFunctionList(map);

        PageInfo pageInfo=new PageInfo(list);
        List<FunctionListOutputPO>  listpo=new ArrayList<FunctionListOutputPO>();
        list.forEach((AdminFunction temp) -> {
            FunctionListOutputPO po=new FunctionListOutputPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

    @Override
    public String addEntity(FunctionAddInputDTO dto)  throws Exception{
        AdminFunction entity=new AdminFunction();
        BeanUtils.copyProperties(dto,entity);
        //检验功能菜单是否存在
        AdminFunction funcInfo = this.adminFunctionDao.selectSameEntity(entity);
        if (funcInfo != null) {
            return FunctionEnum.FunctionAdd.FUNCTION.getValue();
        }
        //检验父菜单是否存在
        AdminFunction parentEntity = null;
        if (entity.getParentId() != null && entity.getParentId() != 0l) {
            parentEntity = this.baseMapper.selectById(entity.getParentId());
            if (parentEntity == null) {
                return FunctionEnum.FunctionAdd.PARENT_FUNCTION.getValue();
            }
        }
        entity.setSortCode(this.getSortCode(parentEntity));
        entity.setIsDel(ComEnum.IsDelete.NORMAL.getValue());
        entity.setIsDisabled(ComEnum.IsDelete.NORMAL.getValue());
        if (entity.getSort() == null) {
            entity.setSort(999l);
        }
        entity.setCreateTime(new Date());

        this.baseMapper.insert(entity);
        return FunctionEnum.FunctionAdd.SUCCESS.getValue();
    }

    /**
     * 获取菜单code
     */
    private String getSortCode(AdminFunction parentEntity) {
        String sortCode = "";
        String maxCode = "";
        Map<String, Object> map = new HashMap<String, Object>();
        if (parentEntity == null) {
            map.put("parentId", 0l);
            map.put("length", 4);
            maxCode = this.adminFunctionDao.selectNextCodeByParentId(map);
            if (maxCode == null) {
                sortCode = "0001";
            } else {
                sortCode = maxCode;
            }
        } else {
            map.put("parentId", parentEntity.getFunModuleId());
            map.put("length", parentEntity.getSortCode().length() + 4);
            maxCode = this.adminFunctionDao.selectNextCodeByParentId(map);
            if (maxCode == null) {
                sortCode = parentEntity.getSortCode() + "0001";
            } else {
                sortCode = maxCode;
            }
        }
        return sortCode;
    }


    @Override
    public String deleteEntityByIds(String ids)throws Exception{
        List<String> listIds = StringUtils.strToList(ids);
        if(listIds == null || listIds.size() == 0){
            return FunctionEnum.FunctionDel.SELECT.getValue();
        }
        for(String id : listIds){
            AdminFunction funcInfo = adminFunctionDao.selectByPrimaryKey(Long.valueOf(id));
            if(funcInfo!=null){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("isDel", ComEnum.IsDelete.DEL.getValue());
                map.put("sortCode", funcInfo.getSortCode());
                this.adminFunctionDao.updateBySortCode(map);
            }
        }
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateEntity(FunctionUpdateInputDTO dto) throws Exception{
        AdminFunction entity=new AdminFunction();
        BeanUtils.copyProperties(dto,entity);
        AdminFunction funcInfo = this.adminFunctionDao.selectSameEntity(entity);
        if (funcInfo != null) {
            return FunctionEnum.FunctionAdd.FUNCTION.getValue();
        }
        this.baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public FunctionDetailOutputDTO findEntityById(Long id) throws Exception{
        FunctionDetailOutputDTO response=new FunctionDetailOutputDTO();
        AdminFunction entity=adminFunctionDao.selectByPrimaryKey(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,response);
            return response;
        }
        return null;
    }

    @Override
    public String updateStatus(String ids,String isDisabled)throws Exception{
        //“禁用”则更新全部关联菜单，“启用”则只更新自己
        List<String> listIds = StringUtils.strToList(ids);
        if(listIds == null || listIds.size() == 0){
            return FunctionEnum.FunctionDel.SELECT.getValue();
        }
        for(String id : listIds){
            AdminFunction funcInfo = adminFunctionDao.selectByPrimaryKey(Long.valueOf(id));
            if(funcInfo!=null){
                if(ComEnum.IsDelete.DEL.getValue().equals(isDisabled)){//禁用
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("isDisabled", isDisabled);
                    map.put("sortCode", funcInfo.getSortCode());
                    this.adminFunctionDao.updateBySortCode(map);
                }else{
                    AdminFunction entity=new AdminFunction();
                    entity.setFunModuleId(funcInfo.getFunModuleId());
                    entity.setIsDisabled(ComEnum.IsDelete.NORMAL.getValue());
                    this.baseMapper.updateById(entity);
                }
            }
        }
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public List<ShouquanInitOutputDTO> findFuncListByRoleId(Long roleId) throws Exception{
        List<ShouquanInitOutputDTO> response=new ArrayList<ShouquanInitOutputDTO>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", 0);
        params.put("roleId", roleId);
        List<AdminFunction> list = this.adminFunctionDao.selectAllListByRole(params);
        if (list == null || list.size() == 0) {
            return null;
        }
        this.readChildSelected(list, roleId);
        BeanUtils.copyProperties(list,response);
        return response;
    }

    /**
     * 加载子集
     *
     * @param list
     * @throws Exception
     */
    private void readChildSelected(List<AdminFunction> list, Long roleId) throws Exception {
        if (list == null || list.size() == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        for (AdminFunction entity : list) {
            params.put("parentId", entity.getFunModuleId());
            List<AdminFunction> listChild = this.adminFunctionDao.selectAllListByRole(params);
            if (listChild == null || listChild.size() == 0) {
                continue;
            }
            entity.setChildFuncs(listChild);
            readChildSelected(listChild, roleId);
        }
    }


    @Override
    public List<AdminFunction> findRoleFuncList(Long roleId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", 0);
        params.put("roleId", roleId);
        List<AdminFunction> list = this.adminFunctionDao.selectRoleFuncList(params);
        if (list == null || list.size() == 0) {
            return null;
        }
        this.readChildByRole(list, roleId);
        return list;
    }

    /**
     * 加载子集
     *
     * @param list
     * @throws Exception
     */
    private void readChildByRole(List<AdminFunction> list, Long roleId) throws Exception {
        if (list == null || list.size() == 0) {
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        for (AdminFunction entity : list) {
            params.put("parentId", entity.getFunModuleId());
            List<AdminFunction> listChild = this.adminFunctionDao.selectRoleFuncList(params);
            if (listChild == null || listChild.size() == 0) {
                continue;
            }
            entity.setChildFuncs(listChild);
            readChildByRole(listChild, roleId);
        }
    }



}
