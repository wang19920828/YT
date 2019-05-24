package org.fh.general.ecom.product.client;

import org.fh.general.ecom.common.base.PagingExtensionVO;
import org.fh.general.ecom.common.dto.order.coupons.CouponsListOutputDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOperUserListDTO;
import org.fh.general.ecom.common.dto.product.order.OutputOrderDetailDTO;
import org.fh.general.ecom.common.dto.product.order.OutputProgrammeCountDTO;
import org.fh.general.ecom.common.dto.product.projectlog.InputProjectOperLogListDTO;
import org.fh.general.ecom.common.po.product.order.InputProgrammePO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author huliping
 * @DATE 2018/9/20
 **/
@FeignClient(name="order")
public interface OrderClient {

    //查询项目预约总金额
    @RequestMapping("RORD8001")
    public OutputOrderDetailDTO findAppointmentAmountByProjectId(@RequestBody String projectId);

    //查询项目认购总金额
    @RequestMapping("RORD8002")
    public OutputOrderDetailDTO findBySubscribeForAmountProjectId(@RequestBody String projectId);


    @RequestMapping("RORD8003")
    public PagingExtensionVO findUserListByProjectId(@RequestBody InputProjectOperLogListDTO dto);


    @RequestMapping("RORD8004")
    public OutputOperUserListDTO findCountUser(@RequestBody String projectId);


    @RequestMapping("RORD8005")
    public OutputProgrammeCountDTO findProgrammeCountById(@RequestBody InputProgrammePO dto);


    @RequestMapping("RCOU8004")
    public CouponsListOutputDTO findCouponByIds(@RequestBody String couponIds);
}
