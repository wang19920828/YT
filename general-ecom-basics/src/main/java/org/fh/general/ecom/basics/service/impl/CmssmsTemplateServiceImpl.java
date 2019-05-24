package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.CmssmsTemplate;
import org.fh.general.ecom.basics.dao.CmssmsTemplateDao;
import org.fh.general.ecom.basics.service.CmssmsTemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
@Service
public class CmssmsTemplateServiceImpl extends ServiceImpl<CmssmsTemplateDao, CmssmsTemplate> implements CmssmsTemplateService {

    @Override
    public String selectByChannelType(String channel, String smsType) {
        CmssmsTemplate template=baseMapper.selectByChannelType(channel, smsType);
        return template.getContent();
    }

    @Override
    public CmssmsTemplate selectByPrimaryKey(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CmssmsTemplate record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CmssmsTemplate record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }
}
