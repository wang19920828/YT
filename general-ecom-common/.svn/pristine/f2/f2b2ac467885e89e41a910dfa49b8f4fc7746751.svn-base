package org.fh.general.ecom.common.dto.order.couponsCode;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

@Data
public class CouponsCodeAddInputDTO {

    @Check(empty = false, description = "优惠券ID")
    private Long couponsId;
    @Check(empty = false, description = "发放类型：1-领用 2-定向发放")
    private String sendType;
    @Check(empty = false, description = "使用业务场景：1-注册 2-投资")
    private String situation;
    @Check(empty = false, description = "发放数量")
    private Long sendNum;
    @Check(empty = false, description = "发放人Id")
    private String userId;
    @Check(empty = false, description = "发放人")
    private String userName;




}
