package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.coupons.CouponsAddInputDTO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsListInputDTO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsListOutputDTO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsUpdateInputDTO;
import org.fh.general.ecom.order.model.Coupons;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
public interface CouponsService extends IService<Coupons> {

    String addEntity(CouponsAddInputDTO dto)throws Exception;

    String deleteEntityById(Long id)throws Exception;

    String updateEntity(CouponsUpdateInputDTO dto)throws Exception;

    CouponsDetailOutputDTO findEntityById(Long id)throws Exception;

    CouponsListOutputDTO findPage(CouponsListInputDTO dto)throws Exception;

    CouponsListOutputDTO findListByOrderSn(String orderSn) throws  Exception;

    CouponsListOutputDTO findLByIds(String ids);
}
