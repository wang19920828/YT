package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.BankCard;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardUpdateInputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
public interface BankCardService extends IService<BankCard> {
    public String deleteByPrimaryKey(Long id);
    public BankCardOutputDTO selectByPrimaryKey(Long id);
    public String insertBankCard(BankCardInsertInputDTO dto);
    public String updateByPrimaryKeySelective(BankCardUpdateInputDTO dto);
    public BankCardListOutputDTO userBankCardList (Long userId);

}
