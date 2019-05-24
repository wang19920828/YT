package org.fh.general.ecom.common.dto.basics.adminFunction;
import lombok.Data;

@Data
public class FunctionListInputDTO {

    private Integer currentPageNum;
    private Integer pageCount;


    private Long parentId;
}
