package org.fh.general.ecom.common.dto.basics.user.userMessage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/29 17:09
 * @Description:
 */
@Data
public class MessageOutputDTO {
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
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
     * 操作人
     */
    private String opName;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date opTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date createTime;
    /**
     * 推送1个人，2部分，3全部
     */
    private String pushType;
    /**
     * 推送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date pushTime;
    /**
     * 图片
     */
    private String img;
    /**
     * 删除0正常1删除
     */
    private String del;
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
