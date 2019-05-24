package org.fh.general.ecom.common.dto.basics.user.userMessage;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/30 14:15
 * @Description:
 */
@Data
public class MessageListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStart;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeEnd;
    /**
     * 标题
     */
    private String title;
    /**
     * 0未发布，1发布，2取消发布
     */
    private String status;
    /**
     *平台
     */
    private String branch;
}
