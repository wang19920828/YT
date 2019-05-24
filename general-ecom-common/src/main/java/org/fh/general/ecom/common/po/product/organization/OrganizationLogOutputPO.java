package org.fh.general.ecom.common.po.product.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/13
 **/
@Slf4j
@Data
public class OrganizationLogOutputPO {

    /**
     * 修改时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date updateDate;
    /**
     * 修改备注
     */
    private String remarks;
    /**
     * 修改用户名
     */

    private String updateName;
}
