package org.fh.general.ecom.common.dto.basics.help.UserGuidePlay;

import lombok.Data;

@Data
public class UserGuidePlayOutputDTO {
    /**
     * 图片
     */
    private String img;
    /**
     * 标题
     */
    private String title;
    /**
     * 平台标识
     */
    private String branch;
    /**
     * id
     */
    private Long id;
    private String del;
}
