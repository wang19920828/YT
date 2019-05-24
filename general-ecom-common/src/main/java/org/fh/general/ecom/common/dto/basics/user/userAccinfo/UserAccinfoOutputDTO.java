package org.fh.general.ecom.common.dto.basics.user.userAccinfo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/19 11:03
 * @Description:
 */
@Data
public class UserAccinfoOutputDTO {
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 总积分
     */
    @TableField("all_credits")
    private Long allCredits;
    /**
     * 关联单号
     */
    @TableField("order_on")
    private String orderOn;
    /**
     * 类型
     */
    private String type;
    /**
     * 消费积分
     */
    private Long credits;
    /**
     * 平台
     */
    private String branch;
    /**
     * 渠道:1-线下,2-微信,3-Android,4-IOS,5-小程序,6-Web,7-Wap
     */
    private String channel;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 类型
     */
    private String typeText;

}
