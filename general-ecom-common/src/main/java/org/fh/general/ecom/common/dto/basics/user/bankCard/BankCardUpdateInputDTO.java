package org.fh.general.ecom.common.dto.basics.user.bankCard;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 14:56
 * @Description:
 */
@Data
public class BankCardUpdateInputDTO {

    private Long id;
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

    private String cardName;

    private String branch;

    private String vaCode;

    private String phone;

}
