package org.fh.general.ecom.common.dto.basics.admin;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;
@Data
public class AdminListInputDTO {

    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;

    @Check(empty = false, description = "分页大小")
    private Integer pageCount;

    private String name;//管理员账号

    private String adminPhone; //手机

    private String realName; //真实姓名

    private String status;//用户状态

    private String branch;//平台编号
}
