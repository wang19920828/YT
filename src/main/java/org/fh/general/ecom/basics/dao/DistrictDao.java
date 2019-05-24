package org.fh.general.ecom.basics.dao;


import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.District;

import java.util.List;
import java.util.Map;

/**
 * @description 行政区域接口
 */
@Mapper
public interface DistrictDao {

	/**
	 * 添加行政区域
	 * @throws Exception
	 */
	public void addDistrict(District District) throws Exception;
	
	/**
	 * 根据id删除行政区域
	 * @param id
	 * @throws Exception
	 */
	public void deleteDistrictById(long id) throws Exception;
	
	/**
	 * 查询所有行政区域
	 * @return
	 * @throws Exception
	 */
	public List<District> findAllDistrict(Map<String, Object> hmParam) throws Exception;
	
	/**
	 * 根据查询条件查询所有数据条数
	 * @param hmParam
	 * @return
	 * @throws Exception
	 */
	public int findDistrictCount(Map<String, Object> hmParam) throws Exception;
	
	/**
	 * 修改行政区域
	 * @param district
	 * @return
	 * @throws Exception
	 */
	public void updateDistrict(District district);

	/**
	 * 根据查询条件查询行政区域
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public District findDistrictById(Long districtId);

	/**
	 * 根据查询条件删除下级行政区域
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public void deleteDistrictByPId(Long districtId);

	/**
	 * 根据查询条件查询下级行政区域
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public List<District> findDistrictByPId(Long districtId);

	/**
	 * 根据查询条件查询行政区域
	 * @param sortCode
	 * @return
	 * @throws Exception
	 */
	public District queryDistrictBySortCode(String sortCode);

	/**
	 * 根据查询条件删除行政区域
	 * @param sortCode
	 * @return
	 * @throws Exception
	 */
	public void deleteDistrictBySortCode(String sortCode);

	/**
	 * 初始化第一级行政区域
	 */	
	public List<District> findDistrictBySLen(Long sortLength);
	/**
	 * 根据地区名模糊查询到地区码（sortCode）
	 */
	public District selectLikeDistrict(Map<String, Object> param);

    List<District> selectAllList();

    District findBycode(String area);
}
