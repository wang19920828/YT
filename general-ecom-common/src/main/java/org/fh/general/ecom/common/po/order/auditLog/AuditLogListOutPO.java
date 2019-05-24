package org.fh.general.ecom.common.po.order.auditLog;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
public class AuditLogListOutPO {

    private Long id;
    private String objectId;
    private String auditName;
    private String business;
    private String results;
    private String remark;
    private Date redTime;
    private Date addTime;
    private String branch;
    private String type;
}
