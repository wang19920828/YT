package org.fh.general.ecom.common.dto.basics.sms.phoneVacode;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/25 14:17
 * @Description:
 */
@Data
public class PhoneVacodeOutputDTO {
    /**
     * 主键id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    @TableField("phone_code")
    private String phoneCode;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 0:未用   1:已经使用
     */
    private String status;
    /**
     * 业务功能码
     */
    @TableField("business_code")
    private String businessCode;
    /**
     * 发送的短信内容
     */
    @TableField("sms_content")
    private String smsContent;
    private String branch;
}
