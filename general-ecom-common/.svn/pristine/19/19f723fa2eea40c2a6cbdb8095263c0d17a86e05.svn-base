package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 18:58
 * @Description:
 */
@Data
public class UseaddPhoneInputDTO {
    /**
     * 用户ID
     */
    @TableId(value="user_id", type= IdType.AUTO)
    private Long userId;

    /**
     * 微信用户识别码
     */
    private String unionid;

    /**
     * 微博用户识别码
     */
    private String weibouid;
    /**
     * PC端QQ登陆识别号
     */
    private String pcqquid;
    /**
     * 手机端QQ登陆识别号
     */
    private String qquid;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 短信验证
     */
    private String vaCode;
    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;

}
