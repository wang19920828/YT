package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.CmsSms;

/**
 * <p>
 * Mapper 接口
 * </p> @author wzy
 * @since 2018-09-21
 */
@Mapper
public interface CmsSmsDao extends BaseMapper<CmsSms> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CmsSms record);

    CmsSms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSms record);

    int updateByPrimaryKey(CmsSms record);

    CmsSms selectByChannel(String channel);

}