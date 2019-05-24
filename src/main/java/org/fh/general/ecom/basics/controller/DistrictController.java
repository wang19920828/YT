package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.District;
import org.fh.general.ecom.basics.service.DistrictService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.district.DistrictDTO;
import org.fh.general.ecom.common.dto.basics.district.DistrictPageDTO;
import org.fh.general.ecom.common.utils.ObjectUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础信息类型(行政区域)管理
 *
 */
@RestController
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	/**
	 * add(行政区域)管理
	 *
	 */
	@RequestMapping("BASI1000")
	public BaseVO addEntity(DistrictDTO dto){
		BaseVO baseVO = new BaseVO();
		District district = new District();
		BeanUtils.copyProperties(dto,district);
		Map<String,Object> paramss = new ConcurrentHashMap<String,Object>();
		paramss.put("district", dto.getDistrict());
		int hasnum = 0 ;
		try {
			hasnum = districtService.findDistrictCount(paramss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(hasnum>0){
			baseVO.exception("地区已存在！");
		}else{
			District parentDistrict = new District();
			if(!"0".equals(dto.getParentId()) && null != dto.getParentId()){
				try {
					parentDistrict = districtService.findDistrictById(Long.valueOf(dto.getParentId()));
				} catch (Exception e) {
					e.printStackTrace();
					baseVO.exception();
				}
			}
			district.changeSortCode(getSortCode(ObjectUtils.isNull(parentDistrict) ? "" : parentDistrict.getSortCode()));
			try {
				districtService.addDistrict(district) ;
				baseVO.success();
			} catch (Exception e) {
				e.printStackTrace();
				baseVO.exception();
			}
		}
		
		return baseVO;
	}
	
	/**
	 * update(行政区域)管理
	 *
	 */
	@RequestMapping("BASI1001")
	public BaseVO updateEntity(DistrictDTO dto){
		BaseVO baseVO = new BaseVO();
		District district = new District();
		try {
			district = districtService.findDistrictById(dto.getDistrictId());
		} catch (Exception e) {
			baseVO.exception("修改的信息不存在！");
			e.printStackTrace();
		}
		BeanUtils.copyProperties(dto,district);
		try {
			districtService.updateDistrict(district);
			baseVO.success();
		} catch (Exception e) {
			baseVO.exception();
			e.printStackTrace();
		}
		return baseVO;
	}
	
	/**
	 * delete(行政区域)管理
	 *
	 */
	@RequestMapping("BASI1002")
	public BaseVO deleteByIds(String districtIds) throws Exception {
		BaseVO baseVO = new BaseVO();
		try {
			//删除功能
			if(StringUtils.isNotEmpty(districtIds)){
				String[] disIdsArr = districtIds.split(",");
				if(disIdsArr != null && disIdsArr.length > 0){
					for(String disId : disIdsArr){
						List<District> childList = districtService.findDistrictByPId(Long.valueOf(disId));
						if(childList !=null && childList.size()>0){
							baseVO.exception("请先删除子节点！");
						}else{
							districtService.deleteDistrictById(Long.valueOf(disId));		
						}
					}
				}
				baseVO.success();
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseVO.exception();
		}
		return baseVO;
	}
	
	/**
	 * 查询所有的地区
	 *
	 */
	/*public BaseVO getAllInit(BaseRequest request,Map<String, Object> params) throws Exception {
		BaseVO baseVO = new BaseVO();
		Long start=System.currentTimeMillis();
		try {
			// List<District> firstDistrict = new ArrayList<District>();
			Object firstDistrict = MongoDbUtil.findList(ConstantsMall.SYS_DISTRICT, params);
			Map<String,Object> districts = new ConcurrentHashMap<String,Object>();
			districts.put("list", firstDistrict);
			baseVO.setbaseVO(districts);			
			baseVO.setSuccess();
		} catch (Exception e) {
			baseVO.setError();
			System.out.println(e);
		}finally{
			Long end=System.currentTimeMillis();
			logger.info("[------------服务结束(执行了：" + (end - start) + "毫秒)------------]");
		}
		
		return baseVO;
	}	*/
	
	/**
	 * 根据父id查询下级的地区(不分页)
	 *
	 */
	@RequestMapping("BASI1003")
	public BaseVO findDistrictByPId(String parentId) throws Exception {
		BaseVO baseVO = new BaseVO();
		try{
			List<District> firstDistrict = districtService.findDistrictByPId(Long.valueOf(parentId));
//			District districtset = new District();
//			if(Long.valueOf(parentId) != 0){
//				District district = districtService.findDistrictById(Long.valueOf(parentId));
//				String sortCode = "";
//				if(district.getSortCode().length()==3){
//					sortCode = "000000";
//					districtset = districtService.queryDistrictBySortCode(sortCode);
//				}else if(district.getSortCode().length()==6){
//					sortCode = "000000000";
//					districtset = districtService.queryDistrictBySortCode(sortCode);					
//				}else if(district.getSortCode().length()==9){
//					sortCode = "000000000000";
//					districtset = districtService.queryDistrictBySortCode(sortCode);					
//				}
//				firstDistrict.add(districtset);
//			}
			if(firstDistrict != null && firstDistrict.size() > 0){
				Map<String,Object> childs = new ConcurrentHashMap<String,Object>();
				childs.put("list", firstDistrict);
				baseVO.success(childs);
			}else{
				baseVO.exception("查无数据！");
			}	
		}catch (Exception e) {
			baseVO.exception();
		}
		return baseVO;
	}
	
	/**
	 * 查询地区(分页)
	 */
	@RequestMapping("BASI1004")
	public PagingVO findPageDistrict(Integer currentPageNum, Integer pageCount, Map<String,Object> params) throws Exception {
		PagingVO pageVO = new PagingVO();
		try{
			if(ObjectUtils.isNull(currentPageNum)){
				currentPageNum=1;
			}
			if(ObjectUtils.isNull(pageCount)){
				pageCount = 10;
			}
			DistrictPageDTO districts = districtService.findAllDistrict(currentPageNum,pageCount,params);
			if(districts != null && districts.getList() != null && districts.getList().size() > 0){
				pageVO.success(districts.getList(),districts.getPageInfo());
			}else{
				pageVO.exception("查无数据！");
			}
		}catch (Exception e) {
			pageVO.exception();
		}
		return pageVO;
	}	
	
	public String getSortCode(String parentSortCode) {
		for (int i = 1; i < 1000; i++)
		{
			String sortCode;
			if (i < 100 && i >9){
				sortCode = parentSortCode + "0" + i;
			}else if(i<10){
				sortCode = parentSortCode + "00" + i;
			}else{
				sortCode = parentSortCode + String.valueOf(i);
			}
			if ((districtService.queryDistrictBySortCode(sortCode))!=null){
				System.out.println(sortCode+"已存在");
			} else{
				return sortCode;
			}
		}
		return "";
	}
}
