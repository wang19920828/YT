package org.fh.general.ecom.basics.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.fh.general.ecom.basics.model.CmsSms;
import org.fh.general.ecom.basics.service.CmsSmsService;
import org.fh.general.ecom.basics.service.PhoneSmsService;
import org.fh.general.ecom.common.utils.HttpUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Date;

@Service
 public class PhoneSmsServiceImpl implements PhoneSmsService {



	@Autowired
	private CmsSmsService cmsSmsService;

	private String SMS_SEND_SUCCESS="1"; //短信接口返回成功 标志

	@Override
	public int sendmsg(String channel,String mobile, String content) {
		int result=0;
		try {
			CmsSms cms=cmsSmsService.selectByChannel(channel);
			if(cms==null){
				return 0;
			}
			if("1".equals(cms.getIsSign())){
				content=cms.getTitle()+content;
				if("base64".equals(cms.getSignName())){
					content =Base64.encodeBase64String(content.getBytes());
				}
			}else{
				content=content+cms.getTitle();
				content=URLEncoder.encode(content, "UTF-8");
			}
			String url=cms.getUrl();
			String param=cms.getParam().replace("${mobile}", mobile).replace("${content}", content);
			if(param.contains("${timeStamp}")){
				param=param.replace("${timeStamp}", new Date().getTime()+"");
			}
			//是否发送短信验证码
			/*if("1".equals(otherParameterService.getVal("SMS_VERIFY", channel))){
				result=1;
				System.out.println("【是否发送短信参数已经关闭，表tb_other_parameter，字段paramkey=SMS_VERIFY，1表示关闭】");
				return result;
			}*/
			String response=HttpUtils.sendPost(url, param);
			if("xml".equals(cms.getSuccessType())){
				StringReader read = new StringReader(response);
				//创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
				InputSource source = new InputSource(read);
				//创建一个新的SAXBuilder
				SAXBuilder sb = new SAXBuilder();
				//通过输入源构造一个Document
				Document doc = sb.build(source);
				//取的根元素
				Element root = doc.getRootElement();
				if("1005".equals(channel)){//排除医疗短信验证码第三方string
					String s = String.valueOf(root.getText());
					if(("100010001").equals(s)){
						result = 1;
					}
				}else {
					String s = root.getChild("returnstatus").getText();
					if(("Success").equals(s)){
						result = 1;
					}
				}

			}else if("String".equals(cms.getSuccessType())){
				result=Integer.parseInt(response);
				if(result!=1){
					result=0;
				}
			}else if("Long".equals(cms.getSuccessType())){
				if(Long.parseLong(response)>0){
					result=1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int parseInt(String str, int def){
		try{
			return Integer.parseInt(str);
		}catch (Exception e) {
		}
		return def;
	}


}
