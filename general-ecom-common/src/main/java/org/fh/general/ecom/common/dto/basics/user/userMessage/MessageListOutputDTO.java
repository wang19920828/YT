package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/30 14:31
 * @Description:
 */
@Data
public class MessageListOutputDTO {
    List<MessageOutputDTO> list;

    PageInfo pageInfo;
}
