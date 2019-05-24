package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.CompanyInformationDao;
import org.fh.general.ecom.basics.model.CompanyInformation;
import org.fh.general.ecom.basics.service.CompanyInformationService;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.CompanyInformation.CompanyInformationOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.CompanyInformationListOutPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        CompanyInformation entity=baseMapper.selectById(id);
        entity.setDel(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public CompanyInformationListOutputDTO findPage(CompanyInformationListInputDTO dto) {
        CompanyInformationListOutputDTO response=new CompanyInformationListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        EntityWrapper<CompanyInformation> wrapper = new EntityWrapper<>();

        wrapper.eq("type",dto.getType());
        wrapper.eq("del",ComEnum.IsDelete.NORMAL.getValue());
        List<CompanyInformation> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<CompanyInformationListOutPO>  listpo=new ArrayList<CompanyInformationListOutPO>();
        list.forEach((CompanyInformation temp) -> {
            CompanyInformationListOutPO po=new CompanyInformationListOutPO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
}
