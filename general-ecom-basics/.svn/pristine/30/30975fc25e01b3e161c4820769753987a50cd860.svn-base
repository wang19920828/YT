package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.Admin;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.admin.*;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登陆
     */
    public AdminLoginOutPutDTO loginAdmin(AdminLoginDTO dto);

    /**
     * 重置管理员密码
     */
    public String resetPwd(Long adminId);

    /**
     * 修改密码
     */
    public String updatePwd(AdminUpdatePwdDTO dto);

    /**
     * 退出登录
     */
    public String loginOff(String browserCode);

    /**
     * 列表展示
     */
    public AdminListOutPutDTO findPage(AdminListInputDTO dto) throws Exception;

    /**
     * 添加
     */
    public String addEntity(AdminAddInputDTO dto) throws Exception;

    /**
     * 删除
     */
    public String deleteEntityById(Long id) throws Exception;

    /**
     * 修改
     */
    public String updateEntity(AdminUpdateInputDTO dto) throws Exception;

    /**
     * 详情
     */
    public AdminDetailOutputDTO findEntityById(Long adminId) throws Exception;

    /***
     * 更新状态
     * */
    public String updateStatus(String ids, String status) throws Exception;

}
