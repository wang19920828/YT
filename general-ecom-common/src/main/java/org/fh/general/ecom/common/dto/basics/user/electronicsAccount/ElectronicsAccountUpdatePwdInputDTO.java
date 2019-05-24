package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/28 11:38
 * @Description:
 */
@Data
public class ElectronicsAccountUpdatePwdInputDTO {
     private String payPassword;
    private String oldPayPwd;
    private Long userId;
    private String phone;
    private String branch;
    private String vaCode;

}
