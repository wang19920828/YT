package org.fh.general.ecom.common.dto.product.consulting;

import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class InputConsultingProjectDelDTO {
    @Check(empty = true,  description = "咨询项目id")
    private Long id;

    @Check(empty = true,  description = "项目id")
    private String projectId;
}
