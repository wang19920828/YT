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
 * 
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
@TableName("tb_cmssms_template")
@Data
public class CmssmsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 渠道1001,1002
     */
	private String channel;
    /**
     * 短信类型1:用户注册、2:找回密码、3:修改密码
     */
	@TableField("sms_type")
	private String smsType;
    /**
     * 短信内容
     */
	private String content;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;




}
