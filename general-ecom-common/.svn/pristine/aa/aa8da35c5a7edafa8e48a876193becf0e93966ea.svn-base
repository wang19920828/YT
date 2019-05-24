/**
 * 
 */
package org.fh.general.ecom.common.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fh.general.ecom.common.base.Entity;


/**
 * @Description
 * @author fengyahui
 * @date 2015年11月2日 上午10:41:12
 */
public class ExportEntity implements Entity {

	private static final long serialVersionUID = 1L;

	//标题
	private List< Map<String,Object>> titles;
	
	//数据
	private List< Map<String,Object>> datas;
	//数据类型
	private  Map<String,Integer> dataTypes;
	
	
	
 
	
	public List<Map<String, Object>> getTitles() {
		return titles;
	}
	public void setTitles(List<Map<String, Object>> titles) {
		this.titles = titles;
	}
	public List<Map<String, Object>> getDatas() {
		return datas;
	}
	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
	public Map<String, Integer> getDataTypes() {
		return dataTypes;
	}
	public void setDataTypes(Map<String, Integer> dataTypes) {
		this.dataTypes = dataTypes;
	}

	/**
	 * 从返回的json串中获取处理结果
	 *  param strJson
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ExportEntity resultToExport(String result){
		ExportEntity entity = new ExportEntity();
		if(StringUtils.isNotEmpty(result)){
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> titles= new ArrayList<Map<String,Object>>();
			JSONObject jsonObject = JSONObject.parseObject(result);
			//处理title
			JSONArray titleArray = jsonObject.getJSONArray("titles");
			for(int i = 0 ;i < titleArray.size();i++){
				Map title =(Map) titleArray.get(i);
				titles.add(title);
			}
			entity.setTitles(titles);
			
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for(int i = 0 ;i < jsonArray.size();i++){
				Map data =(Map) jsonArray.get(i);
				datas.add(data);
			}
			entity.setDatas(datas);
		}
		return entity;
	}
}
