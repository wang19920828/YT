package org.fh.general.ecom.order.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 代金券表
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@Data
@TableName("tb_gold_ticket")
public class GoldTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 代金券编号
     */
	@TableField("ticket_sn")
	private String ticketSn;
	/**
	 * 代金券名称
	 */
	@TableField("ticket_name")
	private String ticketName;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 持有人
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 持有人姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 持有人手机号
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 延期次数
     */
	@TableField("delay_num")
	private Integer delayNum;
    /**
     * 创建时间
     */
	@TableField("add_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
    /**
     * 失效时间
     */
	@TableField("off_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date offTime;
    /**
     * 使用状态: 1-未使用 2-已使用 3-已过期
     */
	@TableField("use_state")
	private String useState;
    /**
     * 订单编号
     */
	@TableField("order_sn")
	private String orderSn;
    /**
     * 平台编号
     */
	private String branch;




}
