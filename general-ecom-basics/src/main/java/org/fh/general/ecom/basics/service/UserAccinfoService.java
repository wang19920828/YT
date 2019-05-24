package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.UserAccinfo;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoOutputDTO;

/**
 * <p>
 *积分表 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
public interface UserAccinfoService extends IService<UserAccinfo> {
    public UserAccinfoOutputDTO selectByPrimaryKey(Long id);
    public String insertUserAccinfo(UserAccinfoInsertInputDTO dto);
    public String insertMinusUserAccinfo(UserAccinfoInsertInputDTO dto);
    public UserAccinfoListOutputDTO findCreditsPage(UserAccinfoListInputDTO dto);
    public UserAccinfoListOutputDTO findAmountPage(UserAccinfoListInputDTO dto);

}
