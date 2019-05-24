package org.fh.general.ecom.common.dto.basics.user.electronicsAccount;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Auther: wangzhanying
 * @Date: 2018/10/8 16:32
 * @Description:
 */
@Data
public class ElectronicsAccountListIntputDTO {
    @Check(empty = false, description = "当前页")
    private Integer currentPageNum;
    @Check(empty = false, description = "分页大小")
    private Integer pageCount;
    /**
     * 电子账户ID
     */
    private Long accountId;
    /**
     * 电子账号
     */
    private String accountNo;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 平台 ：1001-D5厨房；1002-美食工场 ；1003-爱炉火锅；1004-真真小吃；
     */
    private String branch;

    /**
     * 账户状态(00-生效 01-冻结 02-注销)
     */
    private String accountStatus;

    /**
     * 创建开始时间
     */
    private Long createTimeStart;

    /**
     * 创建结束时间
     */
    private Long createTimeEnd;
    /**
     * 最后更新开始时间
     */
    private Long lastTimeStart;
    /**
     * 最后更新结束时间
     */
    private Long lastTimeEnd;




}
