package org.fh.general.ecom.common.dto.basics.user.userAccinfo;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Auther: wangzhanying
 * @Date: 2018/9/19 11:34
 * @Description:
 */
@Data
public class UserAccinfoListInputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private Long userId;
    private String type;
    private String branch;
    private String orderOn;
}
