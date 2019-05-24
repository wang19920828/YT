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
 * 用户站内信息
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@TableName("tb_user_message")
@Data
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
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
	/**
	 * 平台
	 */
	private String branch;
}
