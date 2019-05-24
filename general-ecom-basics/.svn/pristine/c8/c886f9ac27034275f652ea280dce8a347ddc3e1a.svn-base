package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.GuideArticle;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleInputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.GuideArticle.GuideArticleOutputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
public interface GuideArticleService extends IService<GuideArticle> {

    GuideArticleOutputDTO selectByPrimaryKey(Long id);

    String insertUserGuide(GuideArticleInputDTO dto);

    String updateGuideArticle(GuideArticleInputDTO dto);

    String deleteByPrimaryKey(Long id);

    String updatePublish(Long id);

    GuideArticleListOutputDTO findPage(GuideArticleListInputDTO dto);
}
