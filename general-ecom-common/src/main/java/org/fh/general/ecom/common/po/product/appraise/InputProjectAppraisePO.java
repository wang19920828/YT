package org.fh.general.ecom.common.po.product.appraise;

import lombok.Data;

import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/21
 **/
@Data
public class InputProjectAppraisePO {
    private String projectId;
    private Date startTimeStr;
    private Date endTimeStr;
    private String projectName;
    private String createBy;
    private String phone;
    private String branch;
    private String  appraiseType;
    private String appraiseId;
}


