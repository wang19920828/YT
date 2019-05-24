package org.fh.general.ecom.common.dto.basics.user;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
public class MyUserAddressPO {

    private Long id;
    private Long userId;
    /**
     * 是否默认地址 0否 1是
     */
    private String isHost;
    /**
     * 联系人
     */
    private String name;
    /**
     * 用户性别0女1男2不限
     */
    private String sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 详细地址
     */
    private String address;




}
