/**
 * 行政区域实体类
 */
package org.fh.general.ecom.basics.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class District implements Serializable{

	private static final long serialVersionUID = 1L;
	//@TableId(value="district_id", type= IdType.AUTO)
	private Long districtId =0L;//区域id
	//@TableField("district")
	private String district = "";//名称
	//@TableField("pinCode")
	private String pinCode="";//拼音
	//@TableField("areaCode")
	private String areaCode="";//行政区号
	//@TableField("areaType")
	private String areaType="";//区域类型
	//@TableField("parentId")
	private String parentId="0";
	//@TableField("childSK")
	private String childSK = "";//
	//@TableField("priority")
	private Integer priority = 0;//优先级
	//@TableField("sortCode")
	private String sortCode = "";  //编码
	//@TableField("creatorId")
	private Long creatorId = Long.parseLong("0");//创建人
	//@TableField("createTime")
	private Long createTime = System.currentTimeMillis();//创建时间

	List<District>  childDistricts = new ArrayList<District> ();

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getChildSK() {
		return childSK;
	}

	public void setChildSK(String childSK) {
		this.childSK = childSK;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getSortCode() {
		return sortCode;
	}

	public void changeSortCode(String sortCode)
	{
		this.sortCode = sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public List<District> getChildDistricts() {
		return childDistricts;
	}

	public void setChildDistricts(List<District> childDistricts) {
		this.childDistricts = childDistricts;
	}
}
