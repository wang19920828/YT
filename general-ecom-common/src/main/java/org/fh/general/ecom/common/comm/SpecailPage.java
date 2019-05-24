package org.fh.general.ecom.common.comm;

import lombok.Data;
import org.fh.general.ecom.common.base.Entity;


import java.io.Serializable;
import java.util.List;
@Data
public class SpecailPage implements Entity {
	//数据
	//private List<ShopOrder> list;
	//总记录数
	private Integer total;
	//每页显示条数
	private Integer pageSize;
	//当前页
	private Integer pageNum;
	//总页数
//	private Integer pages =(((this.getTotal()==null?0:this.getTotal())-1)/(this.getPageSize()==null?1:this.getPageSize())+1);
	private Integer pages;

	public Integer getPages() {
		return ((total-1)/pageSize+1);
	}
}
