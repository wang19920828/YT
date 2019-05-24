package org.fh.general.ecom.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fh.general.ecom.common.comm.ConvertUtil;


/**
 * Created by pocket on 16/7/6.
 */
public class HttpRequest {

    //设置超时时间
    private int timeout = 10000;

    private static RequestConfig requestConfig;

    private static final HttpRequest httpsRequest = new HttpRequest();

    private HttpRequest(){
        requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectionRequestTimeout(timeout).build();

    }

    public static HttpRequest getHttpsRequestSingleton() {
        return httpsRequest;
    }

    /**
     * 发送get请求
     * @param url
     * @param params
     * @return
     */
    public JSONObject sendGet(String url, Map<String, String> params) {
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        StringBuffer urlParamsBuffer = new StringBuffer();
        while(iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            urlParamsBuffer.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        String getUrl = url;
        if(urlParamsBuffer.length() > 0) {
            urlParamsBuffer.deleteCharAt(urlParamsBuffer.length() - 1);
            getUrl += '?'+ urlParamsBuffer.toString();
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(getUrl);
        httpGet.setConfig(requestConfig);
        JSONObject jsonObject = new JSONObject();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity);
            jsonObject = JSON.parseObject(responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    /**
     * 发送post请求
     * @param url
     * @param params
     * @return
     */
    public static  JSONObject sendPost(String url, Map<String, String> params) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.setConfig(requestConfig);
        JSONObject jsonObject = new JSONObject();
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpost);
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity);
            jsonObject = JSON.parseObject(responseContent);
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    @SuppressWarnings("rawtypes")
	public static String requestOfPost(String url,Map<String,Object> applicationParamsMap)
           {
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            try {
            	Map<String,String> param = new ConcurrentHashMap<String,String>();
    			for (Iterator i = applicationParamsMap.keySet().iterator(); i.hasNext();) {
    		    	Object obj = i.next();
    		    	param.put(obj.toString(), applicationParamsMap.get(obj).toString());
    			 }
    			
                //设置参数
                List<BasicNameValuePair> nameValuePairList = ConvertUtil.convertToEntity(param);
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nameValuePairList,"UTF-8");
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(uefEntity);
                CloseableHttpClient httpClient = HttpClients.createDefault();
                response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } catch (Exception e) {
               e.printStackTrace();
            }finally {
                httpPost.releaseConnection();
                try {
                    if (response != null) {
                        response.close();
                    }else{
                        System.out.println("请检查environment.properties文件是否配置正确");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			return "";

        }
    @SuppressWarnings({ "rawtypes", "deprecation" })
	public static String requestOfPostFile(String url,Map<String,Object> applicationParamsMap, byte[] fileData,
            String imgName){
    	  HttpPost httpPost = new HttpPost(url);
          CloseableHttpResponse response = null;
          try {
              //设置参数
              List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
              
              Map<String,String> p1 = new ConcurrentHashMap<String,String>();
              for (Iterator i = applicationParamsMap.keySet().iterator(); i.hasNext();) {
		    	Object obj = i.next();
		    	p1.put(obj.toString(), applicationParamsMap.get(obj).toString());
	  			    	
  			 }
              List<BasicNameValuePair> nameValuePairList = ConvertUtil.convertToEntity(p1);
              nameValuePairs.addAll(nameValuePairList);

              MultipartEntityBuilder entity = MultipartEntityBuilder.create();
              entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
              entity.setCharset(Charset.forName("utf-8"));  
              ByteArrayBody byteArray = new ByteArrayBody(fileData, imgName);
              entity.addPart("file", byteArray);

              URLEncodedUtils.format(nameValuePairs, "UTF-8");
              Iterator<NameValuePair> it = nameValuePairs.iterator();
              while(it.hasNext()){
                  NameValuePair param = it.next();
                  entity.addPart(param.getName(), new StringBody(param.getValue(), Charset.forName("utf8")));
              }
              HttpEntity httpEntity = entity.build();
              httpPost.setEntity(httpEntity);
              CloseableHttpClient httpClient = HttpClients.createDefault();
              response = httpClient.execute(httpPost);
              HttpEntity responseEntity = response.getEntity();
              return EntityUtils.toString(responseEntity, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
			httpPost.releaseConnection();
			try {
			if (response != null) {
			response.close();
			}else{
				System.out.println("请检查environment.properties文件是否配置正确");
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			return "";

}

}
