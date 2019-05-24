package org.fh.general.ecom.basics.service;


import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.CmssmsTemplate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-21
 */
public interface CmssmsTemplateService extends IService<CmssmsTemplate> {
    String selectByChannelType(String channel, String smsType);

    CmssmsTemplate selectByPrimaryKey(Long id);

    int insertSelective(CmssmsTemplate record);

    int updateByPrimaryKeySelective(CmssmsTemplate record);
	
}
