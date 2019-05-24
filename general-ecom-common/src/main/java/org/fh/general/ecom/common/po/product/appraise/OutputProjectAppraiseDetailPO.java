package org.fh.general.ecom.common.po.product.appraise;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author huliping
 * @DATE 2018/9/21
 **/
@Data
public class OutputProjectAppraiseDetailPO {
    private Long id;
    private Long projectId;
    private String  projectName;
    private String appraiseId;
    private String appraiseContent;
    private String appraiseImg;
    private String status;
    private String appraiseType;
    private String  nickName;
    private String  userImg;
    private String phone;
    private  String  createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String branch;
    private  String branchName;
    private String channel;

}
