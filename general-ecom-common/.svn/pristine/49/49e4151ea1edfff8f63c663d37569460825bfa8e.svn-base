package org.fh.general.ecom.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;


public abstract class ToolUtils {

	private final static String charset = "UTF-8";
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
	 * String型数据Map转换
	 * @param params
	 * @param reParams
	 * @param str
	 * @return
	 */
	public static Map<String, Object> setVal(Map<String, String> params,Map<String, Object> reParams,String []str) {
		for (String string : str) {
			if(StringUtils.isNotEmpty(params.get(string))){
				reParams.put(string, params.get(string));
			}
		}
		return reParams;
	}
	
	/**
	 * String型数据Map转换
	 * @param params
	 * @param reParams
	 * @param str
	 * @return
	 */
	public static Map<String, Object> setValLong(Map<String, String> params,Map<String, Object> reParams,String []str) {
		for (String string : str) {
			if(StringUtils.isNotEmpty(params.get(string))){
				reParams.put(string, Long.valueOf(params.get(string)));
			}
		}
		return reParams;
	}
	
	/**
	 * 生成签名算法第一步  将map中的参数排序后拼接
	 * 将MAP转换为参数的字符串组 以&连接 向指定 URL 发送POST方法的请求
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToString(Map<String, Object> params) {
		List<String> strList = new ArrayList<String>();
		String result="";
		// 遍历map 将map型数据转为String,格式为 name1=value1&name2=value2
		Iterator it = params.entrySet().iterator();
		String key;
		String value;
		// 遍历数组
		while (it.hasNext()) {
			String str = "";
			Map.Entry entry = (Map.Entry) it.next();
			key = entry.getKey().toString();
			value = entry.getValue()==null?"":entry.getValue().toString();
			// 判断值是否为空 如果为空则去除
			if (!StringUtils.isEmpty(value) && !StringUtils.isEmpty(key)) {
				str = key + "=" + value;
				strList.add(str);
			}
		}
		int size = strList.size();  
		String[] arr = (String[])strList.toArray(new String[size]);//使用了第二种接口，返回值和参数均为结果  
		//快排
		QuickSortUtils.QuickSort(arr, 1, size);
		//拼接为字符串
		for(String s:arr){
			result = result+s+"&";
		}
		return result;
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
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getKeyNumByTime() {
		Long time = System.currentTimeMillis();
		String timeStr = time.toString();
		Long forTime = DateUtils.formatDateForWx(time);
		timeStr=forTime+timeStr.substring(timeStr.length()-4,timeStr.length());
		return timeStr;
	}
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, Object> packageParams,String key) {
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
	 * 遍历map 对参数进行拼接
	 * @param map
	 * @param sb
	 */
	@SuppressWarnings("rawtypes")
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

	
	/**
	 * 通过string,string类型Map将入参传入并转为String，Object型Map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParams(
			Map<String, String> keyHashMap, Map<String, Object> request) {
		Map<String, Object> params = new ConcurrentHashMap<String, Object>();
		Iterator it = keyHashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			String keys = (String) key;
			Object value = entry.getValue();
			String requestParams = request.get(keys)==null?"":request.get(keys).toString();
			if (!StringUtils.isEmpty(requestParams)) {
				switch ((String) value) {
				case "Long":
					value = Long.valueOf(requestParams);
					params.put(keys, value);
					break;
				case "Integer":
					value = Integer.valueOf(requestParams);
					params.put(keys, value);
					break;
				case "BigDecimal":
					value = new BigDecimal(requestParams);
					params.put(keys, value);
					break;
				case "String":
					value = requestParams;
					params.put(keys, value);
					break;
				case "Bool":
					value = Integer.valueOf(requestParams);
					if (Integer.valueOf(requestParams).intValue() == 0) {
						params.put(keys, value);
					}
					break;
				case "Date":
					value = requestParams;
					params.put(keys, value);
					params.put(
							"startDate",
							DateUtils.getDate(
									requestParams + " 00:00:00",
									"yyyy-MM-dd HH:mm:ss").getTime());
					params.put(
							"endDate",
							DateUtils.getDate(
									requestParams + " 23:59:59",
									"yyyy-MM-dd HH:mm:ss").getTime());
					break;
				case "StartDate":
					value = requestParams;
					params.put(keys, value);
					params.put(
							"startDate",
							DateUtils.getDate(
									requestParams + " 00:00:00",
									"yyyy-MM-dd HH:mm:ss").getTime());
					break;
				case "EndDate":
					value = requestParams;
					params.put(keys, value);
					params.put(
							"endDate",
							DateUtils.getDate(
									requestParams + " 23:59:59",
									"yyyy-MM-dd HH:mm:ss").getTime());
					break;
				case "List": // 去除List中空值
					value = requestParams;
					String[] value1 = (value.toString()).split(",");
					List<String> list = Arrays.asList(value1);
					List<String> listReal = new ArrayList<String>();
					for (String str : list) {
						if (!str.equals("")) {
							listReal.add(str);
						}
					}
					params.put(keys, listReal);
					break;
				}
			}
		}
		return params;
	}
	
	/**
	 * map转Json
	 * @param params
	 * @return
	 */
	public static String mapToJson(Map<String, Object> params) {
		JSONObject json = new JSONObject();
		Set<String> keySet = params.keySet();
		for(String key:keySet)
		{
		    json.put(key, params.get(key));

		}
		return json.toString();
	}

	/**
	 * 判断long型数据是否为空且大于0
	 * @param xddje
	 * @return
	 */
	public static boolean isLongEmpty(Long xddje) {
		return xddje==null||xddje<=0l;
	}
	
	/**
	 * 判断long型数据是否为空且大于0
	 * @param xddje
	 * @return
	 */
	public static boolean isLongEmptyStr(String xddje) {
		return StringUtils.isEmpty(xddje)||Long.valueOf(xddje)<=0l;
	}
	
	/**
	 * 数组转List<String>
	 * @param xddje
	 * @return
	 */
	public static List<String> changeList(String param) {
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(param)){
			String[] strArray = param.split(",");
			for(String str:strArray){if(!StringUtils.isEmpty(str)){list.add(str);}}
		}
		return list;
	}
	
	@SuppressWarnings("unused")
	public static  String createCode(String maxCode,String parentSortCode){
		int codeLen = 3;
		Long codeNew = 0L;
		if(maxCode.equals("")){
			codeNew  = 0L;
		}else{
			codeNew  = Long.valueOf(maxCode) + 1;
		} 
		 NumberFormat formatter = NumberFormat.getNumberInstance(); 
	   if(parentSortCode.equals("")){
		   formatter.setMinimumIntegerDigits(codeLen);  
	       formatter.setGroupingUsed(false);  
	       String s = formatter.format(codeNew);
	       int len = s.length();
			/*if(len!=maxCode.length()){
				s="0"+s;
			}*/
	       return s;
	   }
       if(maxCode.equals("")){ 
	       formatter.setMinimumIntegerDigits(codeLen );  
	       formatter.setGroupingUsed(false);  
	       String s = formatter.format(codeNew);
    	   s = parentSortCode  +s ;
    	   return  s ;
       }else{
    	   int len  =  maxCode.length() / codeLen ;
    	   formatter.setMinimumIntegerDigits(codeLen *len );  
	       formatter.setGroupingUsed(false);  
	       String code = formatter.format(codeNew);
    	   return code ;
       }
      
	}

	public static int checkMustParams(Map<String, Object> params, String[] str) {
		int x= 0;
		for (int i = 1; i < str.length+1; i++) {
			if(StringUtils.isEmpty(str[i-1])){
				x = i;
			}
		}
		return x;
	}
	
	public static int checkMustParamsStr(Map<String, String> params, String[] str) {
		int x= 0;
		for (int i = 1; i < str.length+1; i++) {
			if(StringUtils.isEmpty(str[i-1])){
				x = i;
			}
		}
		return x;
	}
	
	public  static String addLeftSpace(String str,int countSpace,String newStr)
    {
    	String resultStr=str;
    	if(str.length()>countSpace){
    		return str;
    	}
    	int len= countSpace- str.length();
    	for(int i=0;i<len;i++)
    	{
    		resultStr=	newStr + resultStr;
    	}
    	return resultStr;
    }
}