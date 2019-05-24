package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.Message;
import org.fh.general.ecom.common.dto.basics.user.userMessage.*;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-10-29
 */
public interface MessageService extends IService<Message> {
    /**
     * 添加系统消息
     */
    String insertMessage(MessageInsertInputDTO dto);

    /**
     *记录其他消息，返回id
     */
    Long insertItMessage(MessageInsertItInputDTO dto);

    /**
     * 根据id查询
     */
    MessageOutputDTO findMessagebyId(Long id);
    /**
     *根据id修改
     */
    String updateMessagebyId(MessageUpdateInputDTO dto);
    /**
     *根据id删除
     */
    String deleteMessagebyId(Long id);
    /**
     *根据id发布
     */
    String publishMessagebyId(MessagePublishInputDTO dto);
    /**
     *根据id发布取消
     */
    String publishNotMessagebyId(MessagePublishInputDTO dto);
    /**
     * 分页查询站内信
     */
    public MessageListOutputDTO findPage(MessageListInputDTO dto);
    /**
     * 分页查询系统消息
     */
    public MessageListOutputDTO findXtPage(MessageListInputDTO dto);
}
