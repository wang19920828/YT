package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.EmailDao;
import org.fh.general.ecom.basics.model.Email;
import org.fh.general.ecom.basics.service.EmailService;
import org.fh.general.ecom.common.dto.basics.sms.email.EmailSendInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
/**
 * <p>
 * 邮件 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailDao, Email> implements EmailService {


//    @Autowired
//    private EmailSendInfoDao emailSendInfoDao;
//
//    @Autowired
//    private EmailActInfoDao emailActInfoDao;

    private MimeMessage mimeMsg;
    private Session session;
    private Properties props;
    private String username;
    private String password;
    private Multipart mp;


//    @Override
//    public String sendEmail(EmailSaveInputDTO dto) {
//        // 校验
//        Email email=new Email();
//        email.setEmailChannel(dto.getChannel());
//        Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
//        paramMap.put("channel", dto.getChannel() == null ? "" : dto.getChannel());
//        try {
//            List<Email> list = this.baseMapper.findAllEmail(email);
//            if (list.size() > 0) {
//                if (dto.getEmailAdr()!= null
//                        && dto.getSubject() != null
//                        && dto.getSendContent() != null) {
//                    Email entity = list.get(0);
//                    String host = entity.getSmtp();
//                    String port = entity.getPort();
//                    final String send_user = entity.getEmailAdr();
//                    final String password = entity.getEamilPwd();
//                    String emailAdr = dto.getEmailAdr();
//                    String subject = dto.getSubject();
//                    String sendContent = dto.getSendContent();
//                    // 发送邮件
//                    Properties props = new Properties();
//                    /*
//                     * //自己邮件服务器配置 【 smtp.r8china.com 25 r8china@r8china.com
//                     * 12345_rb】 props.setProperty("mail.transport.protocol",
//                     * "smtp"); props.setProperty("mail.smtp.host", host
//                     * );//smtp.r8china.com props.setProperty("mail.smtp.port",
//                     * port);//25 props.setProperty("mail.smtp.auth", "true");
//                     * props.setProperty("mail.smtp.starttls.enable", "true");
//                     * props.setProperty("mail.smtp.ssl.checkserveridentity",
//                     * "false");
//                     */
//                    // QQ邮件服务器【smtp.exmail.qq.com 465 jwg365@flblc.com Fl1234】
//                    props.put("mail.smtp.host", host);// smtp.exmail.qq.com
//                    props.put("mail.smtp.port", port);// 465
//                    props.put("mail.smtp.starttls.enable", "true");
//                    props.put("mail.smtp.auth", "true");
//                    props.put("mail.smtp.socketFactory.port", port);
//                    props.put("mail.smtp.socketFactory.class",
//                            "javax.net.ssl.SSLSocketFactory");
//                    props.put("mail.smtp.socketFactory.fallback", "false");
//                    props.setProperty("mail.imap.socketFactory.class",
//                            "javax.net.ssl.SSLSocketFactory");
//                    props.setProperty("mail.imap.socketFactory.fallback",
//                            "false");
//                    props.setProperty("mail.imap.port", "993");
//                    props.setProperty("mail.imap.socketFactory.port", "993");
//                    /*
//                     * //自己邮件服务器配置 Authenticator authenticator =new
//                     * MyAuthenticator(send_user,
//                     * password);//发送邮件地址：r8china@r8china.com 密码：12345_rb
//                     * Session session = Session.getDefaultInstance(props,
//                     * authenticator); session.setDebug(true); MimeMessage
//                     * message = new MimeMessage(session);
//                     */
//                    // QQ邮件服务器，需要验证
//                    Session mailSession = Session.getDefaultInstance(props,
//                            new Authenticator() {
//                                @Override
//                                protected PasswordAuthentication getPasswordAuthentication() {
//                                    // TODO Auto-generated method stub
//                                    return new PasswordAuthentication(
//                                            send_user, password);// 发送人地址:jwg365@flblc.com
//                                    // 密码：Fl1234
//                                }
//                            });
//                    MimeMessage message = new MimeMessage(mailSession);
//                    message.setFrom(new InternetAddress(send_user));
//                    message.setRecipient(Message.RecipientType.TO,
//                            new InternetAddress(emailAdr));
//                    message.setSubject(subject);
//                    message.setContent(sendContent, "text/html;charset=GBK");
//                    message.saveChanges();
//                    Transport.send(message);
//                    // 发送--激活记录
//                    if (dto.getSendType() != null && !"".equals(dto.getSendType()) && dto.getSendType().equals("9")) {
//                        // 添加邮箱激活记录
//                        EmailActInfo emailAct=new EmailActInfo();
//                        emailAct.setEmailAddress(emailAdr);
//                        emailAct.setUserId(dto.getUserId());
//                        emailAct.setUserName(dto.getReceiveName());
//                        emailAct.setVeriMachIp(host);
//                        emailAct.setVeriTime(System.currentTimeMillis());
//                        this.emailActInfoDao.insert(emailAct);
//                    } else { // 邮箱发送记录
//                        EmailSendInfo sendEntity = new EmailSendInfo();
//                        sendEntity.setTitle(subject);
//                        sendEntity.setContent(sendContent);
//                        sendEntity.setVeriMachIp(host);
//                        sendEntity.setFtpEmailAdr(send_user);
//                        sendEntity.setReceiveEmail(emailAdr);
//                        sendEntity.setReceiveName(dto.getReceiveName() == null ? "" : dto.getReceiveName());
//                        sendEntity.setType(dto.getSendType() == null ? "" : dto.getSendType());
//                        sendEntity.setOptUserId(dto.getOptUserId()== null ? 0L : dto.getOptUserId());
//                        sendEntity.setOptUserName(dto.getOptUserName() == null ? "" : dto.getOptUserName());
//                        this.emailSendInfoDao.insert(sendEntity);
//                    }
//                    return OutEnum.SUCCESS.getCode();
//                } else {
//                    return OutEnum.MUSTPARAMS.getMessage();
//                }
//            } else {
//                return OutEnum.WARN.getMessage();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return OutEnum.FAIL.getCode();
//    }

    /**
     * TODO
     */
    @Override
    public String sendEMail(EmailSendInputDTO dto) {
        try{
            Email email = this.baseMapper.selectByEmailChannel(dto.getChannel());
            if (email != null) {
                String smtp = email.getSmtp();// smtp服务器
                String from = email.getEmailAdr();
                // 邮件显示名称
                if (StringUtils.isEmpty(dto.getSubject())) {
                    dto.setSubject("云投邮件");// 邮件标题
                }
                String username = from;// 发件人真实的账户名
                String password = email.getEamilPwd();// 发件人密码

                boolean sendResult = sendAndCc(smtp, from, dto.getSendTo(), dto.getCopyTo(), email.getCopyTo(), dto.getSubject(), dto.getContent(), username, password, username, dto.getFileMap());
                if(sendResult){
                    return OutEnum.SUCCESS.getCode();
                }else{
                   return "发送失败！";
                }
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return OutEnum.FAIL.getCode();
    }
    /* 调用sendOut方法完成发送 */
    private boolean sendAndCc(String smtp, String from, String to, String copyto, String blindCopyTo, String subject, String content,
                              String username, String password, String emailName, Map<String, String> fileMap){
       // log.info("sendAndCc to:" + to + ",copyto:" + copyto);
        setSmtpHost(smtp);
        createMimeMessage();
        setNeedAuth(true); // 验证
        if (!setSubject(subject))
            return false;
        if (!setBodyIncludeUrlFile(content, fileMap))
            return false;
        if (!setTo(to))
            return false;
        if (StringUtils.isNotBlank(copyto) && !setCopyTo(copyto))
            return false;
        if (StringUtils.isNotBlank(blindCopyTo) && !setBlindCopyTo(blindCopyTo))
            return false;
        if (!setFrom(from, emailName))
            return false;
        setNamePass(username, password);
        if (!sendOut(copyto))
            return false;
        return true;
    }
    /**
     * 设置密送
     * @return
     */
    private boolean setBlindCopyTo(String blindCopyTo) {
        if (blindCopyTo == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.BCC, (Address[]) InternetAddress.parse(blindCopyTo));//密送
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void setSmtpHost(String hostName) {
        // log.debug("设置系统属性：mail.smtp.host=" + hostName);
        if (props == null) {
            props = System.getProperties();
        }
        props.put("mail.smtp.host", hostName);
    }

    private boolean createMimeMessage() {
        try {
            // log.debug("准备获取邮件会话对象！");
            session = Session.getDefaultInstance(props, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // log.debug("准备创建MIME邮件对象！");
        try {
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();
            mimeMsg.setSentDate(new Date());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* 定义SMTP是否需要验证 */
    private void setNeedAuth(boolean need) {
        // log.debug("设置smtp身份认证：mail.smtp.auth = " + need);
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    /* 定义邮件主题 */
    private boolean setSubject(String mailSubject) {
        // log.debug("定义邮件主题！");
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean setBodyIncludeUrlFile(String mailBody,Map<String, String> fileMap) {
        try {
            BodyPart bp = new MimeBodyPart();

            bp.setContent(mailBody.toString(), "text/html;charset=gb2312");
            mp.addBodyPart(bp);
            if(fileMap!=null){
                Iterator<String> it=fileMap.keySet().iterator();
                while(it.hasNext()){
                    String key=it.next();
                    String path=fileMap.get(key);

                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    URL url = new URL(path);
                    URLDataSource uds=new URLDataSource(url);
                    attachmentBodyPart.setDataHandler(new DataHandler(uds));
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(key));
                    mp.addBodyPart(attachmentBodyPart);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* 定义收信人 */
    private boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* 定义抄送人 */
    private boolean setCopyTo(String copyto) {
        if (copyto == null)
            return false;
        try {
//			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
            mimeMsg.setRecipients(Message.RecipientType.BCC, (Address[]) InternetAddress.parse(copyto));//密送
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* 设置发信人 */
    private boolean setFrom(String from, String emailName) {
        try {

            Address from_address = new InternetAddress(from, emailName);
            mimeMsg.setFrom(from_address); // 发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }

    /* 发送邮件模块 */
    private boolean sendOut(String copyto) {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
          //  System.out.println("开始链接");
          //  log.info("开始链接");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
           // System.out.println("连接结束");
           // log.info("连接结束");
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            if (StringUtils.isNotBlank(copyto)) {
                transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.BCC));
            }

            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        //    log.info(e.getMessage());
            return false;
        }
    }
	
}
