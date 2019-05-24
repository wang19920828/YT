package org.fh.general.ecom.common.dto.basics.user.fundJournal;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/27 16:50
 * @Description:
 */
@Data
public class FundJournalfindInputDTO {
    /**
     * 交易流水号
     */
    private String journalNo;
    /**
     * 收支类型(1-充值，2-消费，3-提现,4:赠送,5:分红)
     */
    @TableField("sz_type")
    private String szType;
}
