package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.CmsSms;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
public interface CmsSmsService extends IService<CmsSms> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CmsSms record);

    CmsSms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSms record);

    int updateByPrimaryKey(CmsSms record);

    CmsSms selectByChannel(String channel);
}
