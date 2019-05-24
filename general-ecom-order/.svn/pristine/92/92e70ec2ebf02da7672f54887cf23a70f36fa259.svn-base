package org.fh.general.ecom.order.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowEntityInputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowEntityOutputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListInputDTO;
import org.fh.general.ecom.common.dto.order.dealFlow.DealFlowListOutputDTO;
import org.fh.general.ecom.order.model.DealFlow;

/**
 * <p>
 * 交易流水 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
public interface DealFlowService extends IService<DealFlow> {


    DealFlowListOutputDTO findPage(DealFlowListInputDTO dto);

    DealFlowEntityOutputDTO findEntityByParams(DealFlowEntityInputDTO dto);
    
}
