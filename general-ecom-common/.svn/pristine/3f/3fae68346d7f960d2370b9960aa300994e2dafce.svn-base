package org.fh.general.ecom.common.dto.order.redProject;


import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RedProjectListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String projectName;
    private String companyName;
    private String shareStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String currentTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String currentTimeEnd;
    private String overStatus;


}
