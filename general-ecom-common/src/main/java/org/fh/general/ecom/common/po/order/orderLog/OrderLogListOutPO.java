package org.fh.general.ecom.common.po.order.orderLog;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderLogListOutPO {

    private String title;
    private BigDecimal amount;
    private String remark;
    private String orderStatusName;
}
