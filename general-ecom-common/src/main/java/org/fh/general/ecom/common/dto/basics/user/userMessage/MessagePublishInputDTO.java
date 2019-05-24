package org.fh.general.ecom.common.dto.basics.user.userMessage;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/30 09:24
 * @Description:
 */
@Data
public class MessagePublishInputDTO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 操作人id
     */
    private Long adminId;
    /**
     * 平台
     */
    private String branch;

}
