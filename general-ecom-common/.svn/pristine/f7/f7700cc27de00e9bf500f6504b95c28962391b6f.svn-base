package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 16:59
 * @Description:
 */
@Data
public class UserUpdateInputDTO {

    /**
     * 用户ID
     */
    @TableId(value="user_id", type= IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 个人头像
     */
    @TableField("user_img")
    private String userImg;
    /**
     * 微信
     */
    private String weixin;
    /**
     * 邮箱
     */
    private String email;


}
