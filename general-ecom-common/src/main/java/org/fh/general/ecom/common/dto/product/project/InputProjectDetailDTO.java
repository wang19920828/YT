package org.fh.general.ecom.common.dto.product.project;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class InputProjectDetailDTO {

    @Check(empty = false,description = "项目id")
    private  Long  id ;
}
