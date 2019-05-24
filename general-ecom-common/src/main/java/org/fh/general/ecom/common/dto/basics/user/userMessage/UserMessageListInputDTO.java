package org.fh.general.ecom.common.dto.basics.user.userMessage;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/18 17:57
 * @Description:
 */
@Data
public class UserMessageListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private Long userId;
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
     * 删除标记，0正常，1移除
     */
    private String del;
    /**
     * 状态：0未看，1已看
     */
    private String status;
    /**
     * 类型 1站内信 2项目 3账户
     */
    private String type;

    private String branch;

}
