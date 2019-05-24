package org.fh.general.ecom.common.po.order.couponsCode;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MyCouponsCodePO {

    private String yhm;
    private String couponName;
    private BigDecimal manAmount;
    private BigDecimal amount;
    private Long couponsId;
    private String situation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date offTime;
    private String type; //优惠券类型:1-项目代金券 2-商品满减券 3-商品抵现券'
    private String rule;

}
