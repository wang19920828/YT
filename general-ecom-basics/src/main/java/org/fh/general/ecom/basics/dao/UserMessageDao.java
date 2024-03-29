package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.UserMessage;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputPO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageUpdateByPidInputPO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageUpdateInputDTO;

/**
 * <p>
  * 用户站内信息 Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@Mapper
public interface UserMessageDao extends BaseMapper<UserMessage> {
   public Integer updateUserMessageTypeStatus (UserMessageUpdateInputDTO entity);
   Integer insertByIds(UserMessageInsertInputPO dto);
   public Integer updateByPid (UserMessageUpdateByPidInputPO dto);
}