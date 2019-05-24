package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.ExtraCode;
import org.fh.general.ecom.basics.dao.ExtraCodeDao;
import org.fh.general.ecom.basics.service.ExtraCodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.common.dto.basics.extraCode.VerfifyExtraCodeOutPutDTO;
import org.fh.general.ecom.common.dto.basics.extraCode.VerfifyInputDTO;
import org.fh.general.ecom.common.enumeration.basics.ExtraCodeEnum;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@Service
public class ExtraCodeServiceImpl extends ServiceImpl<ExtraCodeDao, ExtraCode> implements ExtraCodeService {

    @Value("${extra_code_validity}")
    private String expirDate;// 附加码验证的 有效时间 以秒为单位
    private String NOT_ALLOW_SEND = "必须在expirDate之内登陆，超时请重新获取！";

    @Override
    public String addExtraCode(String browserCode) throws Exception {
        String code = StringUtils.getRandomMsgCode(6);
        ExtraCode extraCode = new ExtraCode();
        extraCode.setBrowserCode(browserCode);
        extraCode.setCreateTime(new Date());
        extraCode.setStatus(ComEnum.IsDelete.NORMAL.getValue());
        extraCode.setExtraCode(code);
        baseMapper.insert(extraCode);
        return code;
    }

    @Override
    public VerfifyExtraCodeOutPutDTO verifyExtraCode(VerfifyInputDTO dto) throws Exception {
        VerfifyExtraCodeOutPutDTO out = new VerfifyExtraCodeOutPutDTO();
        try {
            ExtraCode params = new ExtraCode();
            if (StringUtils.isNotEmpty(dto.getBrowserCode())) {
                params.setBrowserCode(dto.getBrowserCode());
            }
            if (StringUtils.isNotEmpty(dto.getBrowserCode())) {
                params.setExtraCode(dto.getExtraCode());
            }
            params.setStatus(ComEnum.IsDelete.NORMAL.getValue());
            ExtraCode extraCode = this.baseMapper.selectOne(params);
            if (extraCode == null) {
                out.setCode(ExtraCodeEnum.VerifyExtraCode.INFO_STATUS_FAULT.getValue());
                return out;
            }

            Long diffTime = (System.currentTimeMillis() - extraCode.getCreateTime().getTime());
            if (Long.valueOf(expirDate).longValue() < diffTime.longValue()) {
                String mistake = NOT_ALLOW_SEND.replaceAll("expirDate", expirDate);
                out.setCode(ExtraCodeEnum.VerifyExtraCode.FAIL.getValue());
                out.setMessage(mistake);
                return out;
            }
            extraCode.setStatus(ComEnum.IsDelete.DEL.getValue());
            this.baseMapper.updateById(extraCode);
            out.setCode(ExtraCodeEnum.VerifyExtraCode.SUCCESS.getValue());

        } catch (Exception e) {
            out.setCode(ExtraCodeEnum.VerifyExtraCode.FAIL.getValue());
            e.printStackTrace();
        }
        return out;
    }

}
