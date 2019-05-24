package org.fh.general.ecom.common.dto.product.project;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.product.project.ProjectProgrammeListOutputPO;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/19
 **/
@Data
public class OutputProjectProgrammeListDTO {

    private List<ProjectProgrammeListOutputPO> projectProgrammeListOutputPOList;

    PageInfo pageInfo;
}
