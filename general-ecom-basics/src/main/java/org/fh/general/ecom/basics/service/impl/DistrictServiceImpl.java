package org.fh.general.ecom.basics.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.DistrictDao;
import org.fh.general.ecom.basics.model.District;
import org.fh.general.ecom.basics.service.DistrictService;
import org.fh.general.ecom.common.dto.basics.district.DistrictDTO;
import org.fh.general.ecom.common.dto.basics.district.DistrictPageDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictDao, District> implements DistrictService {


	@Override
	public DistrictPageDTO findTreeAllList() {
		DistrictPageDTO districtPageDTO=new  DistrictPageDTO();
		EntityWrapper wrapper=new EntityWrapper();
		wrapper.where("parentId={0}",0L);
		List<District> list=baseMapper.selectList(wrapper);
		if(list==null ){
			return null;
		}
		List<DistrictDTO>  listDto = new ArrayList<DistrictDTO>();
		if(list!=null && list.size()>0){
			list.forEach((District temp)->{
				DistrictDTO dto = new DistrictDTO();
				BeanUtils.copyProperties(temp,dto);
				EntityWrapper wrapper1=new EntityWrapper();
				wrapper1.where("parentId={0}",temp.getDistrictId());

				List<District > twoLevel = baseMapper.selectList(wrapper1);

				if(twoLevel!=null && twoLevel.size()>0){
					List<DistrictDTO>  twoListDto = new ArrayList<DistrictDTO>();
					twoLevel.forEach((District two)->{
						DistrictDTO twoDto=new DistrictDTO();
						BeanUtils.copyProperties(two,twoDto);
						EntityWrapper wrapper2=new EntityWrapper();
						wrapper2.where("parentId={0}",two.getDistrictId());
						List<District > threeList = baseMapper.selectList(wrapper2);
						if(threeList!=null && threeList.size()>0){
							List<DistrictDTO>  threeListDto = new ArrayList<DistrictDTO>();
							threeList.forEach((District three)->{
								DistrictDTO threeDto=new DistrictDTO();
								BeanUtils.copyProperties(three,threeDto);
								threeListDto.add(threeDto);
							});
							twoDto.setChildList(threeListDto);
						}
						twoListDto.add(twoDto);
					});
					dto.setChildList(twoListDto);
				}
				listDto.add(dto);
			});
		}
		districtPageDTO.setList(listDto);
		return districtPageDTO;
	}

	@Override
	public  DistrictPageDTO findOneLevelList(){
		DistrictPageDTO districtPageDTO=new  DistrictPageDTO();
		EntityWrapper wrapper=new EntityWrapper();
		wrapper.where("parentId={0}",0L);
		List<District> list=baseMapper.selectList(wrapper);
		if(list==null ){
			return null;
		}
		List<DistrictDTO>  listDto = new ArrayList<DistrictDTO>();
		if(list!=null && list.size()>0){
			list.forEach((District temp)->{
				DistrictDTO dto = new DistrictDTO();
				BeanUtils.copyProperties(temp,dto);
				listDto.add(dto);
			});
		}
		districtPageDTO.setList(listDto);
		return districtPageDTO;
	}

	@Override
	public String findBySortCode( String code) {
		District district=new District();
		district.setSortCode(code);
		String  sb = "";
		District district1 = this.baseMapper.selectOne(district);
		if(district1!=null) {
			sb += district1.getDistrict();
			while (district1 != null && district1.getParentId() != 0L) {
				district1 = this.baseMapper.selectById(district1.getParentId());
				sb = district1.getDistrict()+"-" + sb;
			}
		}
		return sb;
	}


}
