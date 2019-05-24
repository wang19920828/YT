package org.fh.general.ecom.basics.controller;

import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.basics.service.WechatAcctInfoService;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.pay.WeiXinSuccessInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.factory.RequestFactory;
import org.fh.general.ecom.common.utils.XmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Controller
@Slf4j
public class NoticeController {

    private static final String CHAR_SET = "UTF-8";

    private WechatAcctInfoService wechatAcctInfoService;


    @ResponseBody
    @RequestMapping(value = "/{payType}")
    private void notice(@PathVariable(value = "payType") String payType, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(CHAR_SET);
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, CHAR_SET));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        Map<String, String> params = RequestFactory.getParams(request);
        log.info("RequestNotice." + payType + ",参数params：" + params);
        String param = sb.toString();
        log.info("支付类型：" + payType + ",参数：param" + param);
        if ("wechatPay".equals(payType)) {
            wechatPayNotice(sb, response);
        }
        in.close();
        inputStream.close();


    }

    /**
     * 微信支付成功通知
     */
    public void wechatPayNotice(StringBuffer sb, HttpServletResponse response) {
        OutCodeEntity out=new OutCodeEntity();
        try {
            WeiXinSuccessInputDTO dto=(WeiXinSuccessInputDTO) XmlUtils.xmlStrToBean(sb.toString(),WeiXinSuccessInputDTO.class);
            out = this.wechatAcctInfoService.weiXinSuccess(dto);
            if (out.getCode().equals(OutEnum.SUCCESS.getCode())) {
                response.getWriter().write(out.getObj().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
