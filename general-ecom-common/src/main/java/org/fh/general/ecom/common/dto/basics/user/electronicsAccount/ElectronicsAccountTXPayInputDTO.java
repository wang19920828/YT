package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/17 15:14
 * @Description:
 */
@Data
public class ElectronicsAccountTXPayInputDTO {
    /**
     * 订单号
     */
    private String orderSn;
    private String  payPassword;
}
