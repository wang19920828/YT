package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.AdminLog;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListOutputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;

/**
 * <p>
 * 管理员操作日志 服务类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
public interface AdminLogService extends IService<AdminLog> {

    public AdminLogListOutputDTO findPage(AdminLogListInputDTO dto)throws Exception;

    public String addEntity(AdminLogAddInputDTO dto)throws Exception;
	
}
