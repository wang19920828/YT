package org.fh.general.ecom.product.service;


import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.product.organization.*;
import org.fh.general.ecom.product.model.OrganizationInfo;

/**
 * <p>
 * 机构信息表 服务类
 * </p>
 *
 * @author hlp
 * @since 2018-09-13
 */
public interface OrganizationInfoService extends IService<OrganizationInfo> {

    public OutputOrganizationListDTO findPage(InputOrganizationListDTO dto);

    public String addOrganization(InputOrganizationAddDTO dto);

    public OutputOrganizationDetailDTO findEntity(String id);

    public OutputOrganizationDetailDTO findDetailByCompanyNo(String companyNo);

    public String updateOrganization(InputOrganizationUpdateDTO dto);

    public  String delOrganization(InputOrganizationDelDTO dto);

    public OutputOrganizationListDTO findList(InputOrganizationListDTO dto);
}
