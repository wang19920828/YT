package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/9 17:55
 * @Description:
 */
@Data
public class ElectronicsAccountRechargeMinusPayInputDTO {

    /**
     * 用户id
     */
    private Long userId;
    private String branch;
    /**
     * 交易金额
     */
    private BigDecimal transAmt;


}
