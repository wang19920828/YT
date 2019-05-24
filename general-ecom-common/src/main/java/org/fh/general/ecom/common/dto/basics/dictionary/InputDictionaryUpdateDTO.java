package org.fh.general.ecom.common.dto.basics.dictionary;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/29
 **/
@Data
public class InputDictionaryUpdateDTO {
    @Check(empty = true,description = "id")
    private Long id ;
    @Check(empty = true,description = "数据值")
    private String value;
    @Check(empty = true,description = "标签名称")
    private String label;
    @Check(empty = true,description = "类型")
    private String type;
    @Check(empty = true,description = "描述")
    private String description;
    private Long sort;

    private String updateBy;

    private String icon;
}

