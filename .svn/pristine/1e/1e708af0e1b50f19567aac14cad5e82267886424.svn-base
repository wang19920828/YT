package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.CmsSms;
import org.fh.general.ecom.basics.dao.CmsSmsDao;
import org.fh.general.ecom.basics.service.CmsSmsService;
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
public class CmsSmsServiceImpl extends ServiceImpl<CmsSmsDao, CmsSms> implements CmsSmsService {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CmsSms record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public CmsSms selectByPrimaryKey(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CmsSms record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CmsSms record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    @Override
    public CmsSms selectByChannel(String channel) {
        return baseMapper.selectByChannel(channel);
    }

}
