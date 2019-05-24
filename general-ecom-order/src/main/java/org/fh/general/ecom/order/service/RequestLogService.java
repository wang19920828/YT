package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.requestLog.RequestLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListInputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogListOutputDTO;
import org.fh.general.ecom.common.dto.order.requestLog.RequestLogUpdateInputDTO;
import org.fh.general.ecom.order.model.RequestLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 请求日志表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
public interface RequestLogService extends IService<RequestLog> {

    String addEntity(RequestLogAddInputDTO dto)throws Exception;

    String deleteEntityById(Long id)throws Exception;

    String updateEntity(RequestLogUpdateInputDTO dto)throws Exception;

    RequestLogDetailOutputDTO findEntityById(Long id)throws Exception;

    RequestLogListOutputDTO findPage(RequestLogListInputDTO dto)throws Exception;
}
