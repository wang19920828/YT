package org.fh.general.ecom.common.dto.basics.sms.blacklist;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/20 16:37
 * @Description:
 */
@Data
public class BlacklistUpdateStatusInputDTO {
    /**
     * 主键s（用逗号分隔）
     */
    private String ids;
    /**
     * 状态：0、启用；1、禁用
     */
    private String status;
}
