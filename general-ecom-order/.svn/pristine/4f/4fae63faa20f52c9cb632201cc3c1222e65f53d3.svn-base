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
 * 分红审核日志表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_red_audit_log")
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单ID
     */
	@TableField("object_id")
	private String objectId;
    /**
     * 操作人
     */
	@TableField("audit_name")
	private String auditName;
    /**
     * 业务类型：分红申请、分红审核、修改
     */
	private String business;
    /**
     * 审核意见： 操作成功、 通过、驳回、修改
     */
	private String results;
    /**
     * 备注
     */
	private String remark;
    /**
     * 分红日期（null表示立即发放）
     */
	@TableField("red_time")
	private Date redTime;
    /**
     * 操作时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 平台编号：1001-云投
     */
	private String branch;
    /**
     * 类型：1-分红审核日志 2-提现审核日志 3-退款审核日志
     */
	private String type;

}
