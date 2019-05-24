package org.fh.general.ecom.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Sign {
    private final static String charset = "UTF-8";

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
        String jsapi_ticket = "sM4AOVdWfPE4DxkXGEs8VFq8S5Ksf944mbBU6hZEgyF8pb0m4d88xMH-bvKh6mvClMDAJRLomQ02Zm3Bl7x4Nw";
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, Object> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };

    public static Map<String, Object> sign(String jsapi_ticket, String url) {
        Map<String, Object> ret = new ConcurrentHashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length,String str) {
        StringBuffer buffer = new StringBuffer(str);
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        return getRandomString(length,VerifyUtils.RANDOM_RANGE_SMALL);
    }
    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(SortedMap<String, Object> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        System.out.println("md5 sb:" + sb);
        String sign = MD5Util.MD5Encode(sb.toString(), charset)
                .toUpperCase();
        System.out.println("packge签名:" + sign);
        return sign;

    }
    /**
     * 将MAP转换为xml 数据拼接的字符串
     *
     */
    public static String mapToXml(Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml>");
        mapToXMLTest2(params, sb);
        sb.append("</xml>");
        return sb.toString();
    }


    /**
     * 遍历map 对参数进行拼接
     * @param map
     * @param sb
     */
    private static void mapToXMLTest2(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null != value && !StringUtils.isEmpty(value.toString())
                    && !StringUtils.isEmpty(key)) {
                if (value.getClass().getName().equals("java.util.ArrayList")) {
                    ArrayList list = (ArrayList) map.get(key);
                    sb.append("<" + key + ">");
                    for (int i = 0; i < list.size(); i++) {
                        HashMap hm = (HashMap) list.get(i);
                        mapToXMLTest2(hm, sb);
                    }
                    sb.append("</" + key + ">");
                } else {
                    if (value instanceof HashMap) {
                        sb.append("<" + key + ">");
                        mapToXMLTest2((HashMap) value, sb);
                        sb.append("</" + key + ">");
                    } else {
                        sb.append("<" + key + ">" + value + "</" + key + ">");
                    }
                }
            }
        }
    }
}

