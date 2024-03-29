package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/28 09:21
 * @Description:
 */
@Data
public class ElectronicsInitAccountInputDTO {

   private Long userId;
   private String branch;
   private String branchName;
   private String channel;
   private String phone;
   private String vaCode;
   /**
    * 支付密码
    */
   private String payPassword;


}
