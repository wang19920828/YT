package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlp
 * @since 2018-09-28
 */
@Data
@TableName("tb_file_norms")
public class FileNorms implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 文件标识
     */
	@TableField("file_flag")
	private String fileFlag;
    /**
     * 文件大小(单位kb)
     */
	@TableField("file_size")
	private Long fileSize;
    /**
     * 文件宽度(单位mm)
     */
	@TableField("file_width")
	private Long fileWidth;
    /**
     * 文件高度(单位mm)
     */
	@TableField("file_height")
	private Long fileHeight;
    /**
     * 文件描述
     */
	@TableField("file_depict")
	private String fileDepict;
    /**
     * 平台 ：1001-D5厨房1002-美食工场 1003-爱炉火锅 1004-真真小吃
     */
	private String branch;
    /**
     * 渠道:1-线下,2-微信,3-Android,4-IOS,5-小程序,6-Web,7-Wap
     */
	private String channel;
    /**
     * 平台名称
     */
	@TableField("branch_name")
	private String branchName;




}
