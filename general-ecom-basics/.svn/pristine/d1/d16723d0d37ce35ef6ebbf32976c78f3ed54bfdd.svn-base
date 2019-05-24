package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.CompanyInformation;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationOutputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-09-20
 */
public interface CompanyInformationService extends IService<CompanyInformation> {

   public CompanyInformationOutputDTO selectByPrimaryKey(Long id);

   public String insertCompanyInformation(CompanyInformationInputDTO dto);

   public String updateCompanyInformation(CompanyInformationInputDTO dto);

   public String deleteByPrimaryKey(Long id);
}
