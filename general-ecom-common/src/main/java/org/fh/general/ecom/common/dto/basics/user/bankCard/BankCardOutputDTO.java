package org.fh.general.ecom.common.dto.basics.user.bankCard;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 15:44
 * @Description:
 */
@Data
public class BankCardOutputDTO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 状态0正常1移除
     */
    private String status;

    private String cardName;

}
