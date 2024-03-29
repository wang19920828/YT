package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/17 11:19
 * @Description:
 */
@Data
public class UserListVO {
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
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date birthday;
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
     * 登录名
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信
     */
    private String weixin;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 登陆次数
     */
    @TableField("login_num")
    private Long loginNum;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date updateTime;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date registerTime;
    /**
     * 积分
     */
    private Long credits;
    /**
     * 上次登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date loginTime;
    /**
     * 登陆IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 等级ID
     */
    @TableField("grade_id")
    private Long gradeId;
    /**
     * 邀请码
     */
    @TableField("invite_code")
    private String inviteCode;
    /**
     * 户口所在地(区)
     */
    @TableField("res_areaId")
    private String resAreaId;
    @TableField("res_address")
    private String resAddress;
    /**
     * 证件类型（1-身份证）
     */
    @TableField("cert_type")
    private String certType;
    /**
     * 证件号码
     */
    @TableField("cert_no")
    private String certNo;
    /**
     * 证件图片1
     */
    @TableField("cert_img1")
    private String certImg1;
    /**
     * 证件图片2
     */
    @TableField("cert_img2")
    private String certImg2;
    /**
     * 证件图片3
     */
    @TableField("cert_img3")
    private String certImg3;
    /**
     * 是否认证：0未认证，1待审核，2审核未通过，3通过
     */
    @TableField("is_name")
    private String isName;
    /**
     * 状态：1-正常 2-注销 3-删除
     */
    private String status;
    /**
     * 平台：1001-D5厨房 1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
    private String branch;
    /**
     * 平台名称
     */
    @TableField("branch_name")
    private String branchName;
    /**
     * 渠道:1-线下,2-微信(公众号),3-Android（app）,4-IOS（app）,5-小程序,6-Web,7-Wap
     */
    private String channel;
    /**
     * 账户
     */
    private String account;
    /**
     * 支付密码状态0未设定，1，有支付密码
     */
    @TableField("is_payPwd")
    private String isPayPwd;
    /**
     * 备注
     */
    private String remark;
}
