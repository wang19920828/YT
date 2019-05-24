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
 * @since 2018-09-18
 */
@TableName("tb_bank_card")
@Data
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 客户id
     */
	@TableField("cust_id")
	private Long custId;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 银行
     */
	@TableField("bank_code")
	private String bankCode;
    /**
     * 银行卡号
     */
	@TableField("account_no")
	private String accountNo;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态0正常1移除
     */
	private String status;



}
