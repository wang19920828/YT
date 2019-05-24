package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/29 16:47
 * @Description:
 */
@Data
public class MessageInsertItInputDTO {

    /**
     * 类型1，站内，2项目，3账户，4积分，5优惠
     */
    private String type;
    /**
     * 0未发布，1发布，2取消发布
     */
    private String status;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String content;

    /**
     * 推送1个人，2部分，3全部
     */
    @TableField("push_type")
    private String pushType;

    /**
     * 图片
     */
    private String img;

    /**
     * 关联
     */
    private String rele;
    /**
     * 平台
     */
    private String branch;
    /**
     * 标记
     */
    private String flag;
    /**
     * 备用
     */
    private String standby;
}
