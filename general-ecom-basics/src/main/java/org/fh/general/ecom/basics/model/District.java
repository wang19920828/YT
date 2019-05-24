package org.fh.general.ecom.basics.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hlp
 * @since 2018-09-29
 */
@Data
@TableName("tb_district")
public class District implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="districtId", type= IdType.AUTO)
	private Long districtId;
	/**
	 * 区域名称
	 */
	private String district;
	/**
	 * 区域编码
	 */
	private String sortCode;
	/**
	 * 上级区域id
	 */
	private Long parentId;
	/**
	 * 拼音简称
	 */
	private String pinCode;
	/**
	 * 行政区域码
	 */
	private String areaCode;
	/**
	 * 排序
	 */
	private Long priority;
	/**
	 * 子集（对应达达快递城市区域码）
	 */
	private String childSK;
	/**
	 * 区域类型
	 */
	private String areaType;
	/**
	 * 创建人
	 */
	private Long creatorId;
	private Long createTime;



}
