package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
@TableName("tb_cms_sms")
@Data
public class CmsSms implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 渠道
     */
	private String channel;
    /**
     * 地址
     */
	private String url;
    /**
     * 参数
     */
	private String param;
    /**
     * 短信签名名称
     */
	private String title;
    /**
     * 是否加时间戳
     */
	@TableField("is_TimeStamp")
	private String isTimeStamp;
    /**
     * 时间戳命名称
     */
	@TableField("timeStamp_name")
	private String timeStampName;
    /**
     * 是否加验签
     */
	@TableField("is_sign")
	private String isSign;
	@TableField("sign_name")
	private String signName;
    /**
     * 返回类型
     */
	@TableField("success_type")
	private String successType;
	@TableField("create_time")
	private Long createTime;



}
