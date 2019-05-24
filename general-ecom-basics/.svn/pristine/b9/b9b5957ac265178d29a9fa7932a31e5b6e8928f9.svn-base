package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.UserGuidePlay;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.UserGuidePlayInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.UserGuidePlayListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.UserGuidePlayListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.UserGuidePlayOutputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-10-22
 */
public interface UserGuidePlayService extends IService<UserGuidePlay> {

    UserGuidePlayOutputDTO selectByPrimaryKey(Long id);

    String insertUserGuidePlay(UserGuidePlayInputDTO dto);

    String updateUserGuidePlay(UserGuidePlayInputDTO dto);

    String deleteByPrimaryKey(long l);

    UserGuidePlayListOutputDTO findPage(UserGuidePlayListInputDTO dto);
}
