package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/26 15:06
 * @Description:
 */
@Data
public class UserAddWeixinInputDTO {
    /**
     */
    @TableId(value="user_id", type= IdType.AUTO)
    private Long userId;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别 0-女,1-男
     */
    private String sex;
    /**
     * 个人头像
     */
    @TableField("user_img")
    private String userImg;

    /**
     * 微信用户识别码
     */
    private String unionid;

    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;

}
