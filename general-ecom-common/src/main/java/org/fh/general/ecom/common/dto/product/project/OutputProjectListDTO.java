package org.fh.general.ecom.common.dto.product.project;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.po.product.organization.OrganizationListOutputPO;
import org.fh.general.ecom.common.po.product.project.ProjectListOutputPO;

import java.util.List;

/**
 * @Author huliping
 * @DATE 2018/9/17
 **/
@Data
public class OutputProjectListDTO {
    List<ProjectListOutputPO> list;

    PageInfo pageInfo;
}
