package org.fh.general.ecom.common.dto.order.redAudit;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RedAuditListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String projectName;
    private String companyName;
    /**
     * 本期分红日期-开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String currentTimeStart;

    /**
     * 本期分红日期-结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String currentTimeEnd;
    /**
     * 申请时间-开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addTimeStart;

    /**
     *申请时间-结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addTimeEnd;
}
