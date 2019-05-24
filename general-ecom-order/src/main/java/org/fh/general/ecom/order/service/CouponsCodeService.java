package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeAddInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeListInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeListOutputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeUpdateInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.MyCouponsCodeListInputDTO;
import org.fh.general.ecom.common.po.order.couponsCode.MyCouponsCodePO;
import org.fh.general.ecom.order.model.CouponsCode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠码表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
public interface CouponsCodeService extends IService<CouponsCode> {

    String addEntity(CouponsCodeAddInputDTO dto)throws Exception;

    String deleteEntityById(Long id)throws Exception;

    String updateEntity(CouponsCodeUpdateInputDTO dto)throws Exception;

    CouponsCodeDetailOutputDTO findEntityById(Long id)throws Exception;

    CouponsCodeListOutputDTO findPage(CouponsCodeListInputDTO dto)throws Exception;

    CouponsCodeListOutputDTO findListByOrderSn(String orderSn) throws  Exception;

    List<MyCouponsCodePO> myCouponsCodeList(MyCouponsCodeListInputDTO dto) ;

}
