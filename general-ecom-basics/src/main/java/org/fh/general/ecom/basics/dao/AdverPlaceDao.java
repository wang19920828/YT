package org.fh.general.ecom.basics.dao;

import org.fh.general.ecom.basics.model.AdverPlace;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
public interface AdverPlaceDao extends BaseMapper<AdverPlace> {
    public AdverPlace selectBySign(String placeSign);
}