package org.fh.general.ecom.common.dto.basics.user;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/28 18:43
 * @Description:
 */
@Data
public class UserPhoneLoginInputDTO {

    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;
    /**
     * 手机号
     */
    private String phone;

    private String vaCode;


}
