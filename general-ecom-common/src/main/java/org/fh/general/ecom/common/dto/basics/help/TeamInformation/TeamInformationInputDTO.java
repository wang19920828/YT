package org.fh.general.ecom.common.dto.basics.help.TeamInformation;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class TeamInformationInputDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 标题
     */
    private String type;
    /**
     * 子标题
     */
    private String subtitle;
    /**
     * 照片
     */
    private String photo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职务
     */
    private String job;
    /**
     * 详细信息
     */
    private String imformation;
    /**
     * 事件发生时间
     */
    @TableField("happen_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date happenDate;

}
