package org.fh.general.ecom.common.vo.basics.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ParamListVO {

    private String paramName;

    private String paramKey;

    private String parentParamName;

    private String paramType;

    private String status;

    private Long sort;

    private String paramDesc;

    private String paramValue;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss"  ,timezone="GMT+8")
    private Date updateTime;

    public ParamListVO() {
    }

    public ParamListVO(String paramName, String paramKey, String parentParamName, String paramType, String status, Long sort, String paramDesc, String paramValue, String remark, Date createTime, Date updateTime) {
        this.paramName = paramName;
        this.paramKey = paramKey;
        this.parentParamName = parentParamName;
        this.paramType = paramType;
        this.status = status;
        this.sort = sort;
        this.paramDesc = paramDesc;
        this.paramValue = paramValue;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
