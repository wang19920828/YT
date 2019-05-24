package org.fh.general.ecom.common.dto.basics.user;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/9 15:47
 * @Description:
 */
@Data
public class UserUpdateRealInputDTO {

    private Long userId;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别 0-女,1-男
     */
    private String sex;

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




}
