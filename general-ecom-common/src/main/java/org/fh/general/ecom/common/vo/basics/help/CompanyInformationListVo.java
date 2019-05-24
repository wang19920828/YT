package org.fh.general.ecom.common.vo.basics.help;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class CompanyInformationListVo {
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
    /**
     * 是否删除
     */
    private String del;
}
