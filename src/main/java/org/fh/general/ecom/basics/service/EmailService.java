package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.Email;
import org.fh.general.ecom.common.dto.basics.sms.email.EmailSendInputDTO;

/**
 * <p>
 * 邮件 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
public interface EmailService extends IService<Email> {
    /**
     * 发送邮件
     */
    //public String sendEmail(EmailSaveInputDTO dto);

    public String sendEMail(EmailSendInputDTO dto);
}
