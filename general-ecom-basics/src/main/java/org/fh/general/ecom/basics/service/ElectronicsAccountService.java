package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.ElectronicsAccount;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.user.electronicsAccount.*;

/**
 * <p>
 * 电子账户基本信息表 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-27
 */
public interface ElectronicsAccountService extends IService<ElectronicsAccount> {
   String initAccount (ElectronicsInitAccountInputDTO dto);
   OutCodeEntity createAccountPwd(ElectronicsAccountPwdInputDTO dto);
   String updatePayPwd (ElectronicsAccountUpdatePwdInputDTO dto);
   ElectronicsAccountOutputDTO findAccountByUserId (Long userId);
   String rechargeRedPay (ElectronicsAccountRechargePayInputDTO dto);
   String refundPay (ElectronicsAccountRechargePayInputDTO dto);
   String rechargeXFsPay (ElectronicsAccountTXPayInputDTO dto);
   String topUpPay (ElectronicsAccountTopUPPayInputDTO dto);
   String accountFreeze (ElectronicsAccountFreezeOutputDTO dto);
   ElectronicsAccountListOutputDTO findPage(ElectronicsAccountListIntputDTO dto);
   ElectronicsAccount findByUserId (Long userId);
   String rechargeMinusPay (ElectronicsAccountRechargeMinusPayInputDTO dto);
   String updateForgetPayPwd (ElectronicsAccountForgetPwdInputDTO dto);
}
