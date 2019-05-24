package org.fh.general.ecom.basics.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.DistrictDao;
import org.fh.general.ecom.basics.model.District;
import org.fh.general.ecom.basics.service.DistrictService;
import org.fh.general.ecom.common.dto.basics.district.DistrictDTO;
import org.fh.general.ecom.common.dto.basics.district.DistrictPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDao districtDao;
	
	/**
	 * 分页查询行政区域
	 * @param currentPageNum  当前第几页
	 * @param pageCount  每页多少条
	 * @param hmParam  查询条件
	 * @return
	 * @throws Exception
	 */
	@Override
	public DistrictPageDTO findAllDistrict(Integer currentPageNum, Integer pageCount, Map<String, Object> hmParam)throws Exception {
		PageHelper.startPage(currentPageNum,pageCount);
		List<District> list = districtDao.findAllDistrict(hmParam);
		PageInfo pageInfo = new PageInfo<>(list);
		List<DistrictDTO> dtoList = new ArrayList<>();
		dtoList = list.stream().map(e->new DistrictDTO(e.getDistrictId(),e.getDistrict(),e.getPinCode(),e.getAreaCode(),
				e.getAreaType(),e.getParentId(),e.getChildSK(),e.getPriority(),e.getSortCode(),e.getCreatorId(),e.getCreateTime())).collect(Collectors.toList());
		DistrictPageDTO pageDTO = new DistrictPageDTO();
		pageDTO.setList(dtoList);
		pageDTO.setPageInfo(pageInfo);
		return pageDTO;
	}

	/**
	 * 新增行政区域
	 */
	@Override
	public void addDistrict(District district) throws Exception {
		this.districtDao.addDistrict(district);
	}

	/**
	 * 根据id删除行政区域
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteDistrictById(long id) throws Exception {
		this.districtDao.deleteDistrictById(id);
	}

	/**
	 * 根据查询条件查询所有数据的条数
	 * @param hmParam 查询条件
	 * @return
	 * @throws Exception
	 */
	@Override
	public int findDistrictCount(Map<String, Object> hmParam)throws Exception {
		return this.districtDao.findDistrictCount(hmParam);
	}
	
	/**
	 * 编辑行政区域 
	 * @param district
	 * @throws Exception
	 */
	@Override
	public void updateDistrict(District district) throws Exception {
		this.districtDao.updateDistrict(district);
	}


	/**
	 * 根据行政区域id查询行政区域
	 * @return
	 * @throws Exception
	 */
	@Override
	public District findDistrictById(Long districtId) throws Exception {
		return this.districtDao.findDistrictById(districtId);
	}

	@Override
	public void deleteDistrictByPId(Long districtId) {
		this.districtDao.deleteDistrictByPId(districtId);
	}

	@Override
	public List<District> findDistrictByPId(Long districtId) {
		return this.districtDao.findDistrictByPId(districtId);
	}
	
	/*@Override
	 * 
	public List<District> findAllAreaInit(Long sortLength) {
		//省级行政区
		List<District> provinceList = districtDao.findDistrictBySLen(sortLength);
		if(provinceList.size()>0){
			readTree(provinceList);
		}
		return provinceList;
	}
		
	void readTree(List<District> childs){
		for(District dep:childs){
			  List<District>  depC = districtDao.findDistrictByPId(dep.getDistrictId());
			  if(depC !=null){
				  	dep.setChildDistricts(depC);
				  	readTree(depC);
			  }
		}
	}
	
	 */
	
	@Override
	public List<District> findAllAreaInit(Long sortLength) {
		//省级行政区
		List<District> provinceList = districtDao.findDistrictBySLen(sortLength);
		if(provinceList.size()>0){
			for(District child1 : provinceList){
				    //市级行政区
					List<District> cityList =  districtDao.findDistrictByPId(child1.getDistrictId());
					if(cityList.size()>0){
						for(District child2:cityList){
							//区县行政区
								List<District> areaList =  districtDao.findDistrictByPId(child2.getDistrictId());
								if(areaList.size()>0){
								for(District child3:areaList){
								List<District> child3s =  districtDao.findDistrictByPId(child3.getDistrictId());
								child3.setChildDistricts(child3s);								
								}
							}
							child2.setChildDistricts(areaList);
						}
						child1.setChildDistricts(cityList);
					}
			}
		}
		return provinceList;
	}

	@Override
	public District queryDistrictBySortCode(String sortCode) {
		return districtDao.queryDistrictBySortCode(sortCode);
	}

	@Override
	public void deleteDistrictBySortCode(String sortCode) {
		this.districtDao.deleteDistrictBySortCode(sortCode);
	}

	@Override
	public List<District> findDistrictBySLen(Long sortLength) {
		return this.districtDao.findDistrictBySLen(sortLength);
	}

	@Override
	public District selectLikeDistrict(Map<String, Object> param) {
		return districtDao.selectLikeDistrict(param);
	}

    @Override
    public List<District> selectAllList() {
        return districtDao.selectAllList();
    }

    @Override
    public District findBycode(String area) {
        return districtDao.findBycode(area);
    }
}
