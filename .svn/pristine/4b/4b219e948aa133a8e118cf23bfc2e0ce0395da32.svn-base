package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.Blacklist;
import org.fh.general.ecom.common.dto.basics.sms.blacklist.*;

/**
 * <p>
 * 黑名单 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-20
 */
public interface BlacklistService extends IService<Blacklist> {

    String addEntity (BlacklistInsertInputDTO dto);
    String updateEntity (BlacklistUpdateInputDTO dto);
    String updateStatus (BlacklistUpdateStatusInputDTO dto);
    boolean getBlackByParam(BlacklistVerInputDTO dto);
    BlacklistListOutputDTO findPage(BlacklistListInputDTO dto)throws Exception;
}
