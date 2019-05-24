package org.fh.general.ecom.common.dto.basics.user.payRefund;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/18 10:40
 * @Description:
 */
@Data
public class PayRefundUpdateInputDTO {
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 0待审核，1通过，2驳回
     */
    @TableField("tx_status")
    private String txStatus;
    /**
     * 驳回备注
     */
    @TableField("audit_remark")
    private String auditRemark;
    /**
     * 退款状态0，待退款；1已退款
     */
    @TableField("tk_status")
    private String tkStatus;
    /**
     * 审核人id
     */
    @TableField("admin_id")
    private Long adminId;

}
