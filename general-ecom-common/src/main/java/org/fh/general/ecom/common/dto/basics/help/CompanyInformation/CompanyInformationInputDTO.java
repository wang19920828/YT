package org.fh.general.ecom.common.dto.basics.help.CompanyInformation;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class CompanyInformationInputDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subtitle;
    /**
     * 标题类型
     */
    private String type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;
    /**
     * 是否删除
     */
    private String del;
}
