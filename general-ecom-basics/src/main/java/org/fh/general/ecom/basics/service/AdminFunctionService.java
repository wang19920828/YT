package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.AdminFunction;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.adminFunction.*;
import org.fh.general.ecom.common.dto.basics.adminRole.ShouquanInitOutputDTO;

import java.util.List;

/**
 * <p>
 * 功能菜单 服务类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
public interface AdminFunctionService extends IService<AdminFunction> {

    public FunctionListOutputDTO findPage(FunctionListInputDTO dto)throws Exception;

    public String addEntity(FunctionAddInputDTO dto)throws Exception;

    public String deleteEntityByIds(String ids)throws Exception;

    public String updateEntity(FunctionUpdateInputDTO dto)throws Exception;

    public FunctionDetailOutputDTO findEntityById(Long id)throws Exception;

    public String updateStatus(String ids, String isDisabled)throws Exception;

    /**
     * 查询全部菜单按角色标记
     */
    public List<ShouquanInitOutputDTO> findFuncListByRoleId(Long roleId) throws Exception;

    /**
     * 查询角色权限
     */
    public List<AdminFunction> findRoleFuncList(Long roleId) throws Exception;


}
