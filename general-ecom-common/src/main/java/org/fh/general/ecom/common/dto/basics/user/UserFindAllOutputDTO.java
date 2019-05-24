package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/28 18:06
 * @Description:
 */
@Data
public class UserFindAllOutputDTO {
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
     * 登录名
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("register_time")
    private Date registerTime;
    /**
     * 积分
     */
    private Long credits;

    /**
     * 状态：1-正常 2-注销 3-删除
     */
    private String status;

    @TableField("invite_code")
    private String inviteCode;
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
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("update_time")
    private Date updateTime;


    /**
     * 客户号
     */
    @TableId(value="cust_id", type= IdType.AUTO)
    private Long custId;
    /**
     * 真实名（若传一个整体的真实姓名，则存入此字段）
     */
    @TableField("real_name")
    private String realName;
    /**
     * 性别 0-女,1-男
     */
    private String sex;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date birthday;
    /**
     * 户口所在地(区)
     */
    @TableField("res_areaId")
    private String resAreaId;
    /**
     * 户口所在地详细地址
     */
    @TableField("res_address")
    private String resAddress;
    /**
     * 证件号码
     */
    @TableField("cert_id")
    private String certId;
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
     * 职业
     */
    private String job;
    /**
     * 客户状态（1-正常 2-禁用 3 逻辑删除）
     */
    @TableField("cust_status")
    private String custStatus;
    /**
     * 是否认证：0否，1待审核，2审核未通过，3通过
     */
    @TableField("is_name")
    private String isName;
    /**
     * 原因（审核）
     */
    private String reason;

}
