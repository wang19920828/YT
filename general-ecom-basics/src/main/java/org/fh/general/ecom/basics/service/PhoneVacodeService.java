package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.PhoneVacode;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.sms.phoneVacode.PhoneVacodeGetUserInputDTO;
import org.fh.general.ecom.common.dto.basics.sms.phoneVacode.PhoneVacodeListInputDTO;
import org.fh.general.ecom.common.dto.basics.sms.phoneVacode.PhoneVacodeListOutputDTO;
import org.fh.general.ecom.common.dto.basics.sms.phoneVacode.PhoneVacodeSaveInputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-20
 */
public interface PhoneVacodeService extends IService<PhoneVacode> {
    String savePhoneVacode (PhoneVacodeSaveInputDTO dto);
    OutCodeEntity verifyPhoneVacode(String phone , String  vaCode, Long phoneVacodeId, String branch);
    PhoneVacodeListOutputDTO findPage(PhoneVacodeListInputDTO dto)throws Exception;
    String getUserPhoneVacode (PhoneVacodeGetUserInputDTO dto);
}
