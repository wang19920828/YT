package org.fh.general.ecom.common.dto.basics.user;

import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 10:57
 * @Description:
 */
@Data
public class UserThirdLoginInputDTO {
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
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private String channel;
}
