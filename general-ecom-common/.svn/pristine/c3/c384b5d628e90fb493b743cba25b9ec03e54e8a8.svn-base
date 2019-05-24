package org.fh.general.ecom.common.dto.product.project;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.fh.general.ecom.common.valid.Check;

/**
 * @Author huliping
 * @DATE 2018/9/18
 **/
@Data
public class InputProjectAddFileDTO {

    private Long id ;
    /**
     * 附件名称
     */
    @Check(empty = false ,description ="文件名称")
    private String fileName;

    /**
     * 附件地址
     */
    @Check(empty = false ,description ="文件路径")
    private String fileUrl;
}
