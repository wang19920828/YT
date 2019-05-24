package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.OtherParameter;
import org.fh.general.ecom.common.dto.basics.OtherParameter.*;

/**
 * <p>
 * 其它参数 服务类
 * </p>
 *
 * @author wyy
 * @since 2018-10-09
 */
public interface OtherParameterService extends IService<OtherParameter> {


    OtherParameterListOutDTO findOthListByType(OtherParameterListInDTO paramDto);

    String addOtherParameter(InputOtherParameterAddDTO dto);

    String updateOtherParameter(InputOtherParameterUpdateDTO dto);

    String delOtherParameter(InputOtherParameterDelDTO dto);

    OutputOtherParameterDetailDTO findDetail(InputOtherParameterDetailDTO dto);

    OutputOtherParameterListDTO findPage(InputOtherParameterListDTO dto);
}
