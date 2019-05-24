package org.fh.general.ecom.common.dto.basics.user.payRefund;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/15 14:43
 * @Description:
 */
@Data
public class PayRefundInsertInputDTO {
    /**
     * 用户Id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 申请金额
     */
    private BigDecimal amount;
    /**
     * 银行卡id
     */
    private Long bankId;
    /**
     * 申请备注
     */
    @TableField("apply_remark")
    private String applyRemark;
    /**
     * 支付密码
     */
    private String payPassword;


}
