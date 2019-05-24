package org.fh.general.ecom.common.dto.basics.user.payRefund;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/19 17:20
 * @Description:
 */
@Data
public class PayRefundInsertTkInputDTO {
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 退款类型0-筹资失败退款，1-冷静期退款
     */
    @TableField("tk_type")
    private String tkType;
}
