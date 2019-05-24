package org.fh.general.ecom.common.vo.basics.adminrole;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShouquanInitVO {
    private Long funModuleId;

    private String sortCode;

    private String name;

    private String funcUrl;

    private Long parentId;

    private String code;

    private String isDisabled;

    private Long creatorId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private Date createTime;

    private Long sort;

    private String isDel;

    private String channel;

    private String selected;

    private List<ShouquanInitVO> childFunction;

}
