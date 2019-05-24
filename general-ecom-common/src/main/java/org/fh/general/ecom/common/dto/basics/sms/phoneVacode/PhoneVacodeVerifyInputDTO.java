package org.fh.general.ecom.common.dto.basics.sms.phoneVacode;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/25 10:49
 * @Description:
 */
@Data
public class PhoneVacodeVerifyInputDTO {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String VaCode;

    private String branch;
}
