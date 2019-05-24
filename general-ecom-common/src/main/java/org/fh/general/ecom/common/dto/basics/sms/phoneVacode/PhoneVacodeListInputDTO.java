package org.fh.general.ecom.common.dto.basics.sms.phoneVacode;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/25 14:13
 * @Description:
 */
@Data
public class PhoneVacodeListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    
    private String branch;
    /**
     * 手机号
     */
    private String phone;

    private String phoneCode;
    /**
     * 0:未用   1:已经使用
     */
    private String status;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String timeStart;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String timeEnd;
}
