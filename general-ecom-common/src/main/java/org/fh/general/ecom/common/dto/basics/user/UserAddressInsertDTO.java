package org.fh.general.ecom.common.dto.basics.user;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/14 11:29
 * @Description:
 */
@Data
public class UserAddressInsertDTO {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 是否默认地址 0否 1是
     */
    private String isHost;
    /**
     * 联系人
     */
    private String name;

    private String sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 城市编码
     */
    private String districtCode;
    /**
     * 地区编码
     */
    private String areaCode;
}
