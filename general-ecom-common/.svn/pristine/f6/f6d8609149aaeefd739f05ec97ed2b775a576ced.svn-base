package org.fh.general.ecom.common.service.impl;

import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.common.service.PdfService;
import org.fh.general.ecom.common.upload.UploadService;
import org.fh.general.ecom.common.upload.UploadServiceImpl;
import org.fh.general.ecom.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service("pdfService")
public class PdfServiceImpl implements PdfService {


   @Override
       public String createReservePdf(Map<String,String> param) {
           String filePaths="";
           try{
               String webRoot = PdfServiceImpl.class.getResource("/").getPath();
                   String ends = "";

                       if (!webRoot.endsWith("/") && !webRoot.endsWith("\\")) {
                           ends =webRoot+ "/";
                       } else{
                           ends =webRoot;
                       }
               String fontUrl=ends+ "font/MSYH.TTC";
               String templatePath =ends + param.get("templateHtml");
               String filename = DateUtils.formatDateForWx(System.currentTimeMillis()) + ".pdf";
               String tempPath = ends+"/" + filename;

               File file = null;
               OutputStream os = null;
               InputStream is = null;

               InputStreamReader isr = null;
               BufferedReader br = null;

               file = new File(tempPath);
               os = new FileOutputStream(file);
               ITextRenderer renderer = new ITextRenderer();
               ITextFontResolver fontResolver = renderer.getFontResolver();

               log.info("fontUri:" + fontUrl);
               fontResolver.addFont(fontUrl, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

               if (templatePath.contains("http://")) {
                   URL url = new URL(templatePath);
                   is = url.openStream();
               } else {
                   is = new FileInputStream(templatePath);
               }
               isr = new InputStreamReader(is,"UTF-8");
               br = new BufferedReader(isr);

               StringBuffer sb = new StringBuffer();
               String s = null;
               while ((s = br.readLine()) != null) {
                   sb.append(s);
               }
               String str = sb.toString();
               Iterator<String> it = param.keySet().iterator();
               while (it.hasNext()) {
                   String key = it.next();
                   String value = param.get(key) == null ?"":param.get(key);
   //				logger.info("key:"+key +"--"+value);
                   str = str.replaceAll("param." + key, value);
               }

               try {
                   str=str.replaceAll("%3D", "=");
                   str=str.replace("<o:p></o:p>", "");
                   log.info("pdf内容str："+str);
                   renderer.setDocumentFromString(str);
               } catch (Exception e) {
                   log.info("异常内容："+str);
                   e.printStackTrace();
               }

               renderer.layout();
               renderer.createPDF(os);
               renderer.finishPDF();

               br.close();
               isr.close();
               is.close();
               os.close();
                 UploadService fastdfsService=new UploadServiceImpl();
                filePaths = fastdfsService.uploadFile(file, filename);
               file.delete();
               Map<String, Object> map = new HashMap<String, Object>();
               // 返回文件路径名
               map.put("filePath", filePaths);
               map.put("fastdfsUrl", filePaths);
           }catch (Exception e){
               e.printStackTrace();
           }
           return filePaths;
       }
}
