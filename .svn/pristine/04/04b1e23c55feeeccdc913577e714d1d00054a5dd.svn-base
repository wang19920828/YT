package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.AdminLoginLog;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;

/**
 * <p>
 * 管理员登录日志 服务类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
public interface AdminLoginLogService extends IService<AdminLoginLog> {

    public AdminLoginLogListOutputDTO findPage(AdminLoginLogListInputDTO dto)throws Exception;

    public String addEntity(AdminLoginLogAddInputDTO dto)throws Exception;
	
}
