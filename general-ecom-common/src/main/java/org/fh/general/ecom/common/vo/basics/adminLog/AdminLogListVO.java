package org.fh.general.ecom.common.vo.basics.adminLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdminLogListVO {

    private Long adminLogId;

    private Long adminId;

    private Long roleId;

    private String operModule;

    private String operResult;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date operTime;
}
