package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 17:15
 * @Description:
 */
@Data
public class UserUpdatePhoneInputDTO {

    /**
     * 用户ID
     */
    @TableId(value="user_id", type= IdType.AUTO)
    private Long userId;

    /**
     * 登录密码
     */
    @TableField("login_pwd")
    private String loginPwd;

    /**
     * 老手机号
     */
    private String oldPhone;
    /**
     * 新手机号
     */
    private String newPhone;
    /**
     * 验证码
     */
    private String vaCode;

    private String branch;

}
