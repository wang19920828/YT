package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
@TableName("tb_user_guide")
@Data
public class UserGuide implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类型 0用户指南1新闻
	 */
	private String type;
	/**
	 * 预览图片
	 */
	private String img;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private String createTime;
	/**
	 * 创建时间
	 */
	@TableField("update_time")
	private String updateTime;
	/**
	 * 是否删除
	 */
	private String del;
	/**
	 * 文章名称
	 */
	private String title;
	/*
    摘要
    */
	private String summary;
	/*
    来源
     */
	private String source;
	/*
    发布日期
     */
	@TableField("publish_time")
	private  String publishTime;
	/*
    排序
     */
	private  String  sort;
	/*
    是否发布
     */
	private  String  published;

}
