package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 请求日志表
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@Data
@TableName("tb_request_log")
public class RequestLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="auto_id", type= IdType.AUTO)
	private Long autoId;
    /**
     * 交易类型
     */
	@TableField("trans_type")
	private String transType;
    /**
     * 查询关键字
     */
	private String keyword;
    /**
     * 功能名称
     */
	@TableField("function_name")
	private String functionName;
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
     * 扩展字段1
     */
	private String ext1;
    /**
     * 扩展字段2
     */
	private String ext2;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;



}
