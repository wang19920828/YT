package org.fh.general.ecom.common.comm;



import org.fh.general.ecom.common.base.Entity;

import java.util.List;

public class Page<T> implements Entity {

	private static final long serialVersionUID = 1L;
	// 总条数
	private int totalRowCount = 0;
	// 总页数
	@SuppressWarnings("unused")
	private int totalPage = 0;
	// 每页条数
	private int pageRowCount = 0;
	// 当前页数
	private int currentPageNu = 0;
	// 起始条数
	private int startPageRowCount = 0;
	// 结束条数
	@SuppressWarnings("unused")
	private int endPageRowCount = 0;
	// 数据列表
	private List<T> datas;

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getTotalPage() {
		int totalPageNum = 0;
		if (this.pageRowCount == 0) {
			return totalPageNum;
		}
		if (this.totalRowCount % this.pageRowCount != 0) {
			totalPageNum = (this.totalRowCount / this.pageRowCount) + 1;
		} else {
			totalPageNum = this.totalRowCount / this.pageRowCount;
		}
		return totalPageNum;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	public int getCurrentPageNu() {
		getTotalPage();
		return this.currentPageNu > this.getTotalPage() ? this.getTotalPage() : this.currentPageNu;
	}

	public void setCurrentPageNu(int currentPageNu) {
		this.currentPageNu = currentPageNu;
	}

	public int getStartPageRowCount() {
		return (this.currentPageNu - 1) * this.pageRowCount;
	}

	public void setStartPageRowCount(int startPageRowCount) {
		this.startPageRowCount = startPageRowCount;
	}

	public int getEndPageRowCount() {
		return startPageRowCount + pageRowCount;
	}

	public void setEndPageRowCount(int endPageRowCount) {
		this.endPageRowCount = endPageRowCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
}
