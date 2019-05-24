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
 * @since 2018-10-11
 */
@Data
@TableName("tb_pay_wechat_acct_info")
public class WechatAcctInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="wechat_id", type= IdType.AUTO)
	private Long wechatId;
    /**
     * 渠道编号
     */
	@TableField("channel_no")
	private String channelNo;
    /**
     * 公众账号ID
     */
	@TableField("app_id")
	private String appId;
    /**
     * API密钥
     */
	@TableField("wechat_key")
	private String wechatKey;
    /**
     * 商户号
     */
	@TableField("mch_id")
	private String mchId;
    /**
     * 通知地址
     */
	@TableField("notify_url")
	private String notifyUrl;
    /**
     * 令牌
     */
	private String token;
    /**
     * 应用密钥
     */
	@TableField("secret_key")
	private String secretKey;
    /**
     * 证书名称
     */
	@TableField("certificate_name")
	private String certificateName;


}
