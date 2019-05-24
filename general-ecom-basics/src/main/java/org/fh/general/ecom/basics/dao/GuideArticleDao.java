package org.fh.general.ecom.basics.dao;

import org.fh.general.ecom.basics.model.GuideArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
public interface GuideArticleDao extends BaseMapper<GuideArticle> {

    GuideArticle selectByTitle(String title);
}