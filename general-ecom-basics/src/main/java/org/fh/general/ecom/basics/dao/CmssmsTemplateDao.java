package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fh.general.ecom.basics.model.CmssmsTemplate;

/**
 * <p>
 * Mapper 接口
 * </p> *
 * @author wzy
 * @since 2018-09-21
 */
@Mapper
public interface CmssmsTemplateDao extends BaseMapper<CmssmsTemplate> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CmssmsTemplate record);

    CmssmsTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmssmsTemplate record);

    int updateByPrimaryKey(CmssmsTemplate record);

    CmssmsTemplate selectByChannelType(@Param("channel") String channel,@Param("smsType")String smsType);

}