package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.Adver;
import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverInputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.Adver.AdverOutputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
public interface AdverService extends IService<Adver> {

    AdverListOutputDTO findPage(AdverListInputDTO dto);

    AdverOutputDTO selectByPrimaryKey(Long id);

    String insertAdver(AdverInputDTO dto);

    String updateAdver(AdverInputDTO dto);

    String deleteByPrimaryKey(Long id);

    String updatePublish(AdverInputDTO dto);
}
