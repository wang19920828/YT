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
@TableName("tb_cms_http_reqlog")
public class HttpReqlog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id自动增长
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 交易类型1:互联支付
     */
	@TableField("trans_type")
	private String transType;
    /**
     * 关键字查询
     */
	@TableField("key_word")
	private String keyWord;
    /**
     * 请求参数
     */
	@TableField("req_param")
	private String reqParam;
    /**
     * 返回结果
     */
	@TableField("res_result")
	private String resResult;
    /**
     * 请求url
     */
	@TableField("req_url")
	private String reqUrl;
    /**
     * 扩展字段1
     */
	private String ext1;
    /**
     * 扩展字段2
     */
	private String ext2;
    /**
     * 请求时间
     */
	@TableField("create_time")
	private Long createTime;



}
