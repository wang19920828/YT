package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/8 14:10
 * @Description:
 */
@Data
public class ElectronicsAccountRechargePayInputDTO {



    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 交易金额
     */
    private BigDecimal transAmt;

}
