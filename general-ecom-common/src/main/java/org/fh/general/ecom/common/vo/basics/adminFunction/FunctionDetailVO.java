package org.fh.general.ecom.common.vo.basics.adminFunction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FunctionDetailVO {

    private Long funModuleId;

    private String name;

    private String sortCode;

    private String code;

    private String isDisabled;

    private Long sort;

    private String funcUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private Date createTime;

}
