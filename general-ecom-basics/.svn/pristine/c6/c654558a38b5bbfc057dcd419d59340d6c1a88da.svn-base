package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author wzy
 * @since 2018-10-29
 */
@Data
@TableName("tb_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

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
	@TableField("op_name")
	private String opName;
    /**
     * 操作时间
     */
	@TableField("op_time")
	private Date opTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 推送1个人，2部分，3全部
     */
	@TableField("push_type")
	private String pushType;
    /**
     * 推送时间
     */
	@TableField("push_time")
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
