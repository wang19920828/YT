package org.fh.general.ecom.common.dto.basics.user.userAccinfo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/19 11:10
 * @Description:
 */
@Data
public class UserAccinfoInsertInputDTO {

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
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
     * 渠道:1-线下,2-微信,3-Android,4-IOS,5-小程序,6-Web,7-Wap
     */
    private String channel;
    /**
     * 平台
     */
    private String branch;

}
