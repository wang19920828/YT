package org.fh.general.ecom.common.dto.basics.pay;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/11 14:02
 * @Description:
 */
@Data
public class WxNativePayInputDTO {
    private String orderSn;
    private String channelNo;
    private String branch;
}
