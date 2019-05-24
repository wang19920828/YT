package org.fh.general.ecom.common.dto.basics.help.UserGuide;

import lombok.Data;

@Data
public class UserGuideOutputDTO {
    private static final long serialVersionUID = 1L;
    private String type;
    /**
     * 类型名称
     */
    private String title;
    /*
    *英文名称
    */
    private String summary;

    private Long id;

    private String del;

}