package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/8 16:15
 * @Description:
 */
@Data
public class ElectronicsAccountFreezeOutputDTO {
    /**
     * 账户状态(00-生效 01-冻结 02-注销)
     */
    @TableField("account_status")
    private String accountStatus;
    /**
     * 电子账户ID
     */
    @TableId(value="account_id", type= IdType.AUTO)
    private Long accountId;
}
