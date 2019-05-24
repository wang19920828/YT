package org.fh.general.ecom.common.vo.basics.dictionary;


import lombok.Data;

@Data
public class DictionaryListVo {

    /**
     *   值
     */
    private String value;

    /**
     *   标签
     */
    private String label;
    /**
     *   大类型
     */
    private String type;

    private String remarks;


    private Long id ;

    private String branch;

    private String description;

    private Long sort;

    private String icon;


}
