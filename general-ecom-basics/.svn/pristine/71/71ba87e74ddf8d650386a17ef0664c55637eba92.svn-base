package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.UserGuide;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideOutputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
public interface UserGuideService extends IService<UserGuide> {

    String updateUserGuide(UserGuideInputDTO dto);

    UserGuideOutputDTO selectByPrimaryKey(Long id);

    String insertUserGuide(UserGuideInputDTO dto);

    String deleteByPrimaryKey(Long id);

    UserGuideListOutputDTO findPage(UserGuideListInputDTO dto);
}
