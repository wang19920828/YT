package org.fh.general.ecom.common.comm;

import java.io.Serializable;

public class CmsConstants implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//新闻是否被选中
	 public static final String ARTICLE_CHOSEN = "0";//未被选中
	 public static final String ARTICLE_CHOSEN_YES = "1"; //选中
	 public static final String ARTICLE_CHOSEN_NO = "2"; //撤销选中
	 
	 //新闻状态
	 public static final String ARTICLE_STATUS_YES = "1"; //发布
	 public static final String ARTICLE_STATUS_NO = "0"; //未发布
	 
	 //文章类别
	 public static final String ARTICLE_CATEGORY_0="0";//其他
	 public static final String ARTICLE_CATEGORY_1="1";//新闻
	 public static final String ARTICLE_CATEGORY_2="2";//动态
	 
	 //新闻范围
	 public static final String ARTICLE_RANGE_1="1";//平台
	 public static final String ARTICLE_RANGE_2="2";//行业
	 public static final String ARTICLE_RANGE_3="3";//项目
	 
	 //路演进行状态
	 public static final String PROJECTSHOW_RUNSTATUS_0="0";//未进行
	 public static final String PROJECTSHOW_RUNSTATUS_1="1";//进行中
	 public static final String PROJECTSHOW_RUNSTATUS_2="2";//结束
	
	
	
	

}
