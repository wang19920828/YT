package org.fh.general.ecom.common.vo.basics.adminFunction;

import lombok.Data;

@Data
public class FunctionListVO {

    private Long funModuleId;
    
    private String name;

    private String sortCode;

    private String code;

    private String isDisabled;

    private Long sort;

}
