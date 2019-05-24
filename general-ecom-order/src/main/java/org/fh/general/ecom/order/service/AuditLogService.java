package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.auditLog.AuditLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListInputDTO;
import org.fh.general.ecom.common.dto.order.auditLog.AuditLogListOutputDTO;
import org.fh.general.ecom.order.model.AuditLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 分红审核日志表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface AuditLogService extends IService<AuditLog> {

    AuditLogListOutputDTO findPage(AuditLogListInputDTO dto)throws Exception;
    
    
    String addEntity(AuditLogAddInputDTO dto)throws Exception;
    
    
}
