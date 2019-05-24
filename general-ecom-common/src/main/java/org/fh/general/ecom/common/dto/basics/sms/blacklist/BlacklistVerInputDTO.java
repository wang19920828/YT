package org.fh.general.ecom.common.dto.basics.sms.blacklist;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/20 15:24
 * @Description:
 */
@Data
public class BlacklistVerInputDTO {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 平台编号：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃
     */
    private String branch;

}
