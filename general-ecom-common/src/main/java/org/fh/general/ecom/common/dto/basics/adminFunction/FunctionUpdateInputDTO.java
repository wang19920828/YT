package org.fh.general.ecom.common.dto.basics.adminFunction;


import lombok.Data;

@Data
public class FunctionUpdateInputDTO {

    private Long funModuleId;

    private String name;

    private String funcUrl;

    private String code;

    private Long sort;

    private Long parentId;

}
