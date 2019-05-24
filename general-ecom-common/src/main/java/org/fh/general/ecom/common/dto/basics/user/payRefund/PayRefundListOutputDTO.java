package org.fh.general.ecom.common.dto.basics.user.payRefund;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/17 18:46
 * @Description:
 */
@Data
public class PayRefundListOutputDTO {
    List<PayRefundOutputDTO> list;

    PageInfo pageInfo;
}
