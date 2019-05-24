package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.AdminRole;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.adminRole.*;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
public interface AdminRoleService extends IService<AdminRole> {

    public RoleListOutputDTO findPage(RoleListInputDTO dto) throws Exception;

    public String addEntity(RoleAddInputDTO dto) throws Exception;

    public String deleteEntityById(Long id) throws Exception;

    public String updateEntity(RoleUpdateInputDTO dto) throws Exception;

    public RoleDetailOutputDTO findEntityById(Long id) throws Exception;

    /***
     * 更新状态
     * */
    public String updateStatus(String ids, String isDisabled)throws Exception;

    /**
     * 保存授权
     */
    public String saveShouquan(ShouquanSaveInputDTO dto) throws Exception;

    public RoleListOutputDTO selectRoleList(String branch);

    public Boolean selectByName(String branch,String roleName);

}
