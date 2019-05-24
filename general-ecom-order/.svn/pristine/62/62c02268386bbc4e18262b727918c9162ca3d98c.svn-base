package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListOutputDTO;
import org.fh.general.ecom.order.model.CouponsLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 优惠券发放记录表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface CouponsLogService extends IService<CouponsLog> {


    CouponsLogListOutputDTO findPage(CouponsLogListInputDTO dto)throws Exception;


    String addEntity(CouponsLogAddInputDTO dto)throws Exception;
}
