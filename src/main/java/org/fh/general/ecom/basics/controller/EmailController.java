package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.EmailService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.dto.basics.sms.email.EmailSendInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 邮件 前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    /**
     * 发送邮件
     */
//    @RequestMapping("EML00001")
//    public BaseVO sendEmail(EmailSaveInputDTO dto){
//        BaseVO baseVO = new BaseVO();
//        String code = emailService.sendEmail(dto);
//        if(!OutEnum.SUCCESS.getCode().equals(code)){
//            baseVO.error(code);
//            return  baseVO;
//        }
//        baseVO.success();
//        return baseVO;
//    }
    /**
     * 发送邮件
     */
    @RequestMapping("EML00001")
    public BaseVO sendEmail(EmailSendInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code = emailService.sendEMail(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

}
