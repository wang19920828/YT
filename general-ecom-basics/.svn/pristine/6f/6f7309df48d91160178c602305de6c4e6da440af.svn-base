package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.CmsParam;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Mapper
public interface CmsParamDao extends BaseMapper<CmsParam> {

    List<CmsParam> selectByParamName(String paramName);
}