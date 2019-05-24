package org.fh.general.ecom.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
@Slf4j
public class HttpUtils {

	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	/**
	 * 执行HTTP POST请求
	 * @param url请求的URL地址
	 * @param queryString 请求的查询参数,可以为null
	 * @param charset 请求编码
	 * @return
	 */
	public static String doPost(String url, Map<String, String> params,
			String charset) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			p.setContentCharset(charset);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	public static String doPost(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpPost post = postForm(url, params);
        body = invoke(httpclient, post);
        httpclient.getConnectionManager().shutdown();
        return body;
    }
 
	private static HttpPost postForm(String url, Map<String, String> params) {
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpost;
    }
	
	
	/**
	 * 执行HTTP GET请求
	 * @param url请求的URL地址
	 * @param queryString 请求的查询参数,可以为null
	 * @return 请求编码
	 */
	public static String doGet(String url, String queryString, String charset) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString)){
				//参数串
				log.info("参数串:"+queryString);
				method.setQueryString(URIUtil.encodeQuery(queryString,charset));
			}
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response =new String(method.getResponseBody(), charset);
			}
		} catch (URIException e) {
			log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response;
	}   
	
	public static String doGet(String url){
		DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);
        httpclient.getConnectionManager().shutdown();
        return body;
	}
	
	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
            HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
	
	private static String invoke(DefaultHttpClient httpclient,
            HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }
	 
	@SuppressWarnings("unused")
	private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        try {
            body = EntityUtils.toString(entity,"UTF-8");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
	
    /**
     * 向指定 URL 发送POST方法的请求 参数为字符串
     * @param url  发送请求的 URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
    		// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	log.error("发送 POST 请求出现异常" + url + "时，发生异常！", e);
        	e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    /**
     * 向指定 URL 发送POST方法的请求 参数为xml
     * @param url
     * @param xmlData
     * @param charset
     * @return
     */
    public static String sendXMLPost(String url, String xmlData,String charset){
    	String strResponse =null;
    	HttpClient client = new HttpClient();
    	PostMethod post  = new PostMethod(url);  
    	charset = charset ==null?"iso-8859-1":charset;
    	try {  
    	    RequestEntity entity = new StringRequestEntity(xmlData, "text/xml",charset);  
    	    post.setRequestEntity(entity);  
    	    client.executeMethod(post);   
    	    int code = post.getStatusCode();  
    	    if (code == HttpStatus.SC_OK)  {
    	    	strResponse = new String(post.getResponseBody(),charset);  
    	    }
    	} catch (Exception ex) {  
    	    ex.printStackTrace();  
    	} finally {  
    	    post.releaseConnection();  
    	}
		return strResponse;  
    }
    
    /**
     * 向指定 URL 发送POST方法的请求 参数为xml
     * @param url
     * @param xmlData
     * @param charset
     * @return
     */
	public static String sendXMLSLLPost(String url, String xmlData,String charset,String SSLURL,String mchPwd)throws Exception{
    	String strResponse =null;
    	charset = charset ==null?"iso-8859-1":charset;
    	  KeyStore keyStore  = KeyStore.getInstance("PKCS12");
          FileInputStream instream = new FileInputStream(new File(SSLURL));
          try {
              keyStore.load(instream, mchPwd.toCharArray());
          } finally {
              instream.close();
          }
          SSLContext sslcontext = SSLContexts.custom()
                  .loadKeyMaterial(keyStore, mchPwd.toCharArray())
                  .build();
          SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                  sslcontext,
                  new String[] { "TLSv1" },
                  null,
                  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
          CloseableHttpClient httpclient = HttpClients.custom()
                  .setSSLSocketFactory(sslsf)
                  .build();
          try {
    		  HttpPost httpost = new HttpPost(url); // 设置响应头信息
    		  httpost.setEntity(new StringEntity(xmlData, charset));
              CloseableHttpResponse response = httpclient.execute(httpost);
              try {
            	  HttpEntity entity = response.getEntity();
                  if (entity != null) {
                	  strResponse = EntityUtils.toString(response.getEntity(), charset);
                     
                  }
              } finally {
                  response.close();
              }
          } finally {
              httpclient.close();
          }
		return strResponse;  
    }
    
	@SuppressWarnings("resource")
	public static String  httpPostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        //String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(json,"UTF-8");
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        se.setContentEncoding("UTF-8");
        httpPost.setEntity(se);
        HttpResponse response = httpClient.execute(httpPost);
        return paseResponse(response);
    } 
}