package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/24 10:29
 * @Description:
 */
@Data
public class UserMessageInsertInputPO {
    /**
     * 用户Ids
     */
    private List<String> userIds;
    /**
     * 信息
     */
    private String message;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String img;
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


    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 父id
     */
    private Long pid;

}
