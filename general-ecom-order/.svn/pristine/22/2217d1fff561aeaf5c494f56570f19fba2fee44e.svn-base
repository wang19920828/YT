package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单操作日志
 * </p>
 *
 * @author pjj
 * @since 2018-08-13
 */
@Data
@TableName("tb_order_log")
public class OrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 子订单编号
     */
	@TableField("order_sn")
	private String orderSn;
    /**
     * 订单状态
     */
	@TableField("order_status_name")
	private String orderStatusName;
    /**
     * 备注
     */
	private String remark;
    /**
     * 处理人姓名
     */
	private String editor;
    /**
     * 操作时间
     */
	@TableField("edit_date")
	private Date editDate;
	/**
	 *  标题
	 */
	private String title;
	/**
	 * 金额
	 */
	private BigDecimal amount;


}
