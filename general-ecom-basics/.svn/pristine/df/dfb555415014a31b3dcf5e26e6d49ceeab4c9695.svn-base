package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.District;
import org.fh.general.ecom.common.dto.basics.district.DistrictPageDTO;

import java.util.List;
import java.util.Map;

public interface DistrictService {

	/**
	 * 分页查询行政区域
	 * @param currentPageNum  当前第几页
	 * @param pageCount  每页多少条
	 * @param params  查询条件
	 * @return
	 * @throws Exception
	 */
	public DistrictPageDTO findAllDistrict(Integer currentPageNum, Integer pageCount, Map<String, Object> params) throws Exception;

	/**
	 * 新增行政区域
	 * @throws Exception
	 */
	public void addDistrict(District district) throws Exception;

	/**
	 * 根据id删除行政区域
	 * @param id
	 * @throws Exception
	 */
	public void deleteDistrictById(long id) throws Exception;

	/**
	 * 根据查询条件查询所有数据的条数
	 * @param hmParam 查询条件
	 * @return
	 * @throws Exception
	 */
	public int findDistrictCount(Map<String, Object> hmParam) throws Exception;

	/**
	 * 根据id查询行政区域
	 * @throws Exception
	 */
	public District findDistrictById(Long districtId) throws Exception;

	/**
	 * 修改行政区域
	 * @throws Exception
	 */
	public void updateDistrict(District district) throws Exception;

	/**
	 * 根据父id删除行政区域
	 */
	public void deleteDistrictByPId(Long districtId);

	/**
	 * 根据父id查询子行政区域
	 */
	public List<District> findDistrictByPId(Long districtId);

	/**
	 * 根据编码查询行政区域
	 */
	public District queryDistrictBySortCode(String sortCode);

	/**
	 * 根据编码删除行政区域
	 */
	public void deleteDistrictBySortCode(String sortCode);

	/**
	 * 初始化全部行政区域
	 */
	public List<District> findAllAreaInit(Long sortLength);

	/**
	 * 初始化第一级行政区域
	 */
	public List<District> findDistrictBySLen(Long sortLength);
	/**
	 * 根据区级模糊查询
	 */
	public District selectLikeDistrict(Map<String, Object> param);

    List<District> selectAllList();

    District findBycode(String area);
}
