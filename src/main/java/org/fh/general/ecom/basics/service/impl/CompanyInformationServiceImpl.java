package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.CompanyInformation;
import org.fh.general.ecom.basics.dao.CompanyInformationDao;
import org.fh.general.ecom.basics.service.CompanyInformationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-09-20
 */
@Service
public class CompanyInformationServiceImpl extends ServiceImpl<CompanyInformationDao, CompanyInformation> implements CompanyInformationService {

    @Override
    public CompanyInformationOutputDTO selectByPrimaryKey(Long id) {
        CompanyInformationOutputDTO entity2=new CompanyInformationOutputDTO();
        CompanyInformation entity=baseMapper.selectById(id);
        BeanUtils.copyProperties(entity,entity2);
        return entity2;
    }

    @Override
    public String insertCompanyInformation(CompanyInformationInputDTO dto) {
        CompanyInformation entity =new CompanyInformation();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateCompanyInformation(CompanyInformationInputDTO dto) {
        CompanyInformation entity =new CompanyInformation();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();

    }

    @Override
    public String deleteByPrimaryKey(Long id) {
        baseMapper.deleteById(id);
        return OutEnum.SUCCESS.getCode();
    }
}
