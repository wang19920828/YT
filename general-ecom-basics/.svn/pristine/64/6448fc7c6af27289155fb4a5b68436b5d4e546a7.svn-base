package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.UserMessage;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageOutputDTO;

/**
 * <p>
 * 用户站内信息 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
public interface UserMessageService extends IService<UserMessage> {
    public String deleteByPrimaryKey(Long id);
    public UserMessageOutputDTO selectByPrimaryKey(Long id);
    public String insertUserMessage(UserMessageInsertInputDTO dto);
    public String updateUserMessage(String ids);
   // public BankCardListOutputDTO userBankCardList (Long userId);
   public UserMessageListOutputDTO findPage(UserMessageListInputDTO dto);
	
}
