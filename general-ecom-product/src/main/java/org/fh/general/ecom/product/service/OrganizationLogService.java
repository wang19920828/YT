package org.fh.general.ecom.product.service;


import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.organization.InputOrganizationUpdateDTO;
import org.fh.general.ecom.common.po.product.organization.OrganizationLogOutputPO;
import org.fh.general.ecom.product.model.OrganizationLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
public interface OrganizationLogService extends IService<OrganizationLog> {

    void insertEntity(InputOrganizationUpdateDTO dto);

    List<OrganizationLogOutputPO> findList(String id);
}
