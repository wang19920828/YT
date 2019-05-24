package org.fh.general.ecom.basics.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.fh.general.ecom.basics.service.ExtraCodeService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.extraCode.VerfifyExtraCodeOutPutDTO;
import org.fh.general.ecom.common.dto.basics.extraCode.VerfifyInputDTO;
import org.fh.general.ecom.common.enumeration.basics.ExtraCodeEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.vo.basics.extraCode.GetExtraCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *  附加码
 * @author wyk
 * @since 2018-09-20
 */
@RestController
public class ExtraCodeController {

    @Autowired
    private ExtraCodeService extraCodeService;


    /**
     * 获取附加验证码
     */
    @RequestMapping("CODE1001")
    public BaseVO getExtraCode(String browserCode){
        BaseVO baseVO=new BaseVO();
        try {
            String response=this.extraCodeService.addExtraCode(browserCode);
            GetExtraCodeVO vo=new GetExtraCodeVO();
            BeanUtils.copyProperties(response, vo);
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }

    /**
     * 校验附加验证码
     */
    @RequestMapping("CODE1002")
    public BaseVO verifyExtraCode(VerfifyInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            VerfifyExtraCodeOutPutDTO out=this.extraCodeService.verifyExtraCode(dto);
            if(!ExtraCodeEnum.VerifyExtraCode.SUCCESS.getValue().equals(out.getCode())){
                baseVO.exception(out.getMessage());
                return baseVO;
            }
            baseVO.success(out);
        } catch (Exception e) {
            baseVO.exception(e.getMessage());
            e.printStackTrace();
        }
        return  baseVO;
    }

	
}
