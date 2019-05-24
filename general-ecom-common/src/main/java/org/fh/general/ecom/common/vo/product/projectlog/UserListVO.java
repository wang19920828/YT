package org.fh.general.ecom.common.vo.product.projectlog;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
@Data
public class UserListVO {



    private Long  totalUserCount;
    private BigDecimal yuyueTotalAmount;
    private BigDecimal regouTotalAmount;
    private BigDecimal redTotalAmount;
}
