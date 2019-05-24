package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 10:03
 * @Description:
 */
@Data
public class UserAttentWeiXinInputDTO {
    /**
     * 微信用户识别码
     */
    private String unionid;
    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private String channel;

    /**
     * 个人头像
     */
    @TableField("user_img")
    private String userImg;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 性别 0-女,1-男
     */
    private String sex;

}
