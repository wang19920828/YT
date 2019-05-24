package org.fh.general.ecom.basics.client;


import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.order.order.OrderEntityOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.fh.general.ecom.common.dto.order.order.UpdatePayStatusInputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/10 18:04
 * @Description:
 */
@FeignClient(name="order")
public interface OrderClient {

    /**
     * 根据单号查订单
     */
   @RequestMapping("ZORD8001")
   public OrderEntityOutDTO findEntityByOrderSnOfPay(@RequestBody String orderSn);

    /**
     * 修改订单支付状态
     */
    @RequestMapping("BOR8004")
    public BaseVO updatePayStatus(@RequestBody UpdatePayStatusInputDTO dto);


    /**
     * 会员列表 查询用户是否有投资项目
     */
    @RequestMapping("ZORD8002")
    public Boolean findUserIsNotProduct(@RequestBody Long userId);

}