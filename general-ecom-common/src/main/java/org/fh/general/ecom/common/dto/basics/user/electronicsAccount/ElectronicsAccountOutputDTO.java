package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/28 10:00
 * @Description:
 */
@Data
public class ElectronicsAccountOutputDTO {
    /**
     * 电子账户ID
     */
    @TableId(value="account_id", type= IdType.AUTO)
    private Long accountId;
    /**
     * 电子账号
     */
    @TableField("account_no")
    private String accountNo;
    /**
     * 账户类型(6200-储蓄帐户)
     */
    @TableField("account_type")
    private String accountType;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 平台 ：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃；
     */
    private String branch;
    /**
     * 平台名称
     */
    @TableField("branch_name")
    private String branchName;

    /**
     * 可用金额
     */
    @TableField("cash_amount")
    private BigDecimal cashAmount;

    /**
     * 账户状态(00-生效 01-冻结 02-注销)
     */
    @TableField("account_status")
    private String accountStatus;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 最后更新时间
     */
    @TableField("last_time")
    private Long lastTime;

}
