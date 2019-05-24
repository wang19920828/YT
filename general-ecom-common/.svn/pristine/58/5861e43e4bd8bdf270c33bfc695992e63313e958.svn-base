package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 16:57
 * @Description:
 */
@Data
public class UserMessageOutputDTO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户Id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 信息
     */
    private String message;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String img;
    /**
     * 删除标记，0正常，1移除
     */
    private String del;
    /**
     * 状态：0未看，1已看
     */
    private String status;
    /**
     * 类型 1站内信 2项目 3账户
     */
    private String type;
    /**
     * 关联字段
     */
    private String corr;

    private String branch;
    /**
     * 标记 当type为2时显示图片标记，默认为1，不显示0
     */
    private String flag;

}
