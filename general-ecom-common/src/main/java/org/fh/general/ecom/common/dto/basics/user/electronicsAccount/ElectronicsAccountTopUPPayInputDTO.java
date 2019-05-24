package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/17 14:30
 * @Description:
 */
@Data
public class ElectronicsAccountTopUPPayInputDTO {
    private Long userId;
    private String branch;
    /**
     * 交易金额
     */
    private BigDecimal transAmt;
}
