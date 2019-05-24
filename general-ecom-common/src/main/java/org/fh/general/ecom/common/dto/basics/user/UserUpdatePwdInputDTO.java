package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 15:53
 * @Description:
 */
@Data
public class UserUpdatePwdInputDTO {
    /**
     * 用户ID
     */
    @TableId(value="user_id", type= IdType.AUTO)
    private Long userId;
    /**
     * 老登陆密码
     */
    private String oldLoginPwd;
    /**
     * 新登陆密码
     */
    private String newLoginPwd;
    /**
     *   验证码
     */
    private String vaCode ;

    private String branch;

}
