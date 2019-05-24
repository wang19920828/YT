package org.fh.general.ecom.common.po.basics.help;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class UserGuideListOutPo {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 类型 0用户指南1新闻
     */
    private String type;
    /**
     * 预览图片
     */
    private String img;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 是否删除
     */
    private String del;
    /**
     * 文章名称
     */
    private String title;
    /*
    摘要
    */
    private String summary;
    /*
    来源
     */
    private String source;
    /*
    发布日期
     */
    private  String publishTime;
    /*
    排序
     */
    private  String  sort;
    /*
    是否发布
     */
    private  String  published;
}
