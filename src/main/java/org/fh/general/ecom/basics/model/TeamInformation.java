package org.fh.general.ecom.basics.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pjj
 * @since 2018-09-20
 */
@TableName("tb_team_information")
@Data
public class TeamInformation implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 标题
     */
	@TableField("type_id")
	private Integer typeId;
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
	private Date happenDate;
	@TableField("create_time")
	private Date createTime;
}
