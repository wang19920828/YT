package org.fh.general.ecom.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.*;


@SuppressWarnings("restriction")
public abstract class StringUtils {
	public static final String EMPTY = "";
	private static final char[] RANDOM_STRING = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	private static final char[] CHAR_ARRAY={
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
		'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
		'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		'W', 'X', 'Y', 'Z'
	};
	private static final char[] NUM_ARRAY={
		'0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9'
	};

	public static boolean isNull(String s) {
		return ObjectUtils.isNull(s);
	}

	public static boolean notNull(String s) {
		return ObjectUtils.notNull(s);
	}

	public static String nullSafe(String s) {
		return (String) ObjectUtils.nullSafe(s, "");
	}

	public static boolean isEmpty(String s) {
		return (s == null) || (s.isEmpty());
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	public static boolean isNotEmpty(Object str) {
		if((str == null || "".equals(str))){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isTrimEmpty(String s) {
		return isEmpty(nullSafe(s).trim());
	}

	public static boolean equals(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		}

		return s1.equals(s2);
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		}

		return s1.equalsIgnoreCase(s2);
	}

	public static String replace(String string, String oldString,
			String newString) {
		if (string == null) {
			return "";
		}

		int i = 0;

		if ((i = string.indexOf(oldString, i)) >= 0) {
			char[] string2 = string.toCharArray();
			char[] newString2 = newString.toCharArray();

			StringBuilder buf = new StringBuilder(string2.length);
			buf.append(string2, 0, i).append(newString2);

			int oldStrLength = oldString.length();
			i += oldStrLength;
			int j = i;

			while ((i = string.indexOf(oldString, i)) > 0) {
				buf.append(string2, j, i - j).append(newString2);
				i += oldStrLength;
				j = i;
			}

			return buf.append(string2, j, string2.length - j).toString();
		}

		return string;
	}

	public static String replace(String string, String oldString,
			String newString, int[] count) {
		if (string == null) {
			return "";
		}

		int i = 0;

		if ((i = string.indexOf(oldString, i)) >= 0) {
			char[] string2 = string.toCharArray();
			char[] newString2 = newString.toCharArray();

			StringBuilder buf = new StringBuilder(string2.length);
			buf.append(string2, 0, i).append(newString2);

			int counter = 1;
			int oldStrLength = oldString.length();
			i += oldStrLength;
			int j = i;

			while ((i = string.indexOf(oldString, i)) > 0) {
				counter++;
				buf.append(string2, j, i - j).append(newString2);
				i += oldStrLength;
				j = i;
			}

			count[0] = counter;
			buf.append(string2, j, string2.length - j);

			return buf.toString();
		}

		return string;
	}

	public static String replaceIgnoreCase(String string, String oldString,
			String newString) {
		if (string == null) {
			return "";
		}

		String lcString = string.toLowerCase();
		String lcOldString = oldString.toLowerCase();

		int i = 0;

		if ((i = lcString.indexOf(lcOldString, i)) >= 0) {
			char[] string2 = string.toCharArray();
			char[] newString2 = newString.toCharArray();

			StringBuilder buf = new StringBuilder(string2.length);
			buf.append(string2, 0, i).append(newString2);

			int oldStrLength = oldString.length();
			i += oldStrLength;
			int j = i;

			while ((i = lcString.indexOf(lcOldString, i)) > 0) {
				buf.append(string2, j, i - j).append(newString2);
				i += oldStrLength;
				j = i;
			}

			return buf.append(string2, j, string2.length - j).toString();
		}

		return string;
	}

	public static String replaceIgnoreCase(String string, String oldString,
			String newString, int[] count) {
		if (string == null) {
			return "";
		}

		String lcString = string.toLowerCase();
		String lcOldString = oldString.toLowerCase();

		int i = 0;

		if ((i = lcString.indexOf(lcOldString, i)) >= 0) {
			char[] string2 = string.toCharArray();
			char[] newString2 = newString.toCharArray();

			StringBuilder buf = new StringBuilder(string2.length);
			buf.append(string2, 0, i).append(newString2);

			int counter = 1;
			int oldStrLength = oldString.length();
			i += oldStrLength;
			int j = i;

			while ((i = lcString.indexOf(lcOldString, i)) > 0) {
				counter++;
				buf.append(string2, j, i - j).append(newString2);
				i += oldStrLength;
				j = i;
			}

			count[0] = counter;
			buf.append(string2, j, string2.length - j);

			return buf.toString();
		}

		return string;
	}

	public static int strLength(String s, String charsetName) {
		if (isEmpty(s)) {
			return 0;
		}

		try {
			return s.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
		}
		return s.getBytes().length;
	}
	
	/**
	 * 字符串转为list
	 * @param strs
	 * @return
	 */
	public static  List<String> strToList(String strs) {
		if(strs == null || strs.length() == 0){
			return null;
		}
		String[] strArr = strs.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<strArr.length ;i++){
			if(!strArr[i].equals("")){
				list.add(strArr[i]);
			}
		}
		return list;
	}

	public static List<Long> strToLongList(String strs)  {
		List<Long> idList = new ArrayList<Long>();
		try {
			if (strs == null || strs.length() == 0) {
				return idList;
			}
			String[] ids = strs.split(",");
			for (String id : ids) {
				if (StringUtils.isNotEmpty(id)) {
					idList.add(Long.valueOf(id));
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return idList;
	}

	public static String substring(String s, int length, String charsetName) {
		try {
			byte[] bytes = nullSafe(s).getBytes(charsetName);

			if (bytes.length <= length) {
				return s;
			}

			if (length < 1) {
				return "";
			}

			int len = s.length();

			for (int i = 0; i < len; i++) {
				int iDestLength = strLength(s.substring(0, i + 1), charsetName);

				if (iDestLength <= length)
					continue;
				if (i == 0) {
					return "";
				}

				return s.substring(0, i);
			}

			return s;
		} catch (UnsupportedEncodingException ex) {
		}
		return "";
	}

	public static String substring(String s, int length, String dot,
			String charsetName) {
		byte[] bytes = nullSafe(s).getBytes();

		if (bytes.length <= length) {
			return s;
		}

		int len = length - dot.length();

		if (len < 1) {
			len = 1;
		}

		return substring(s, len, charsetName) + dot;
	}

	public static String getRandomString(int size) {
		Random random = new Random();
		StringBuffer buf = new StringBuffer(size);

		for (int i = 0; i < size; i++) {
			buf.append(RANDOM_STRING[(Math.abs(random.nextInt()) % RANDOM_STRING.length)]);
		}

		return buf.toString();
	}

	public static String md5String(String s) {
		byte[] digests = (byte[]) null;
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(s.getBytes());
			digests = messageDigest.digest();
		} catch (Exception localException) {
		}

		StringBuffer buf = new StringBuffer();

		for (int offset = 0; offset < digests.length; offset++) {
			int digest = digests[offset];

			if (digest < 0) {
				digest += 256;
			}

			if (digest < 16) {
				buf.append("0");
			}

			buf.append(Integer.toHexString(digest));
		}

		return buf.toString();
	}

	public static String md516String(String s) {
		return md5String(s).substring(8, 24);
	}

	public static String txt2html(String s) {
		if (isTrimEmpty(s)) {
			return "";
		}

		StringBuilder sb = new StringBuilder((int) (s.length() * 1.2D));

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			switch (c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case ' ':
				sb.append("&nbsp;");
				break;
			case '\n':
				sb.append("<br/>");
				break;
			default:
				sb.append(c);
			}

		}

		return sb.toString();
	}

	public static String html2txt(String s) {
		if (isTrimEmpty(s)) {
			return "";
		}

		s = replace(s, "&amp;", "&");
		s = replace(s, "&lt;", "<");
		s = replace(s, "&gt;", ">");
		s = replace(s, "&quot;", "\"");
		s = replace(s, "&nbsp;", " ");
		s = replace(s, "<br/>", "\n");

		return s;
	}

	public static boolean hasChineseCharset(String s) {
		if (s != null) {
			for (int i = 0; i < s.length(); i++) {
				if (s.codePointAt(i) >= 256) {
					return true;
				}
			}
		}

		return false;
	}
	
	
	public static String getRandomPassword(int size){
		Random random = new Random();
		StringBuffer buf = new StringBuffer(size);
		buf.append(getRandomString(size-4));
		for (int i = 0; i < 2; i++) {
			buf.append(CHAR_ARRAY[(Math.abs(random.nextInt()) % CHAR_ARRAY.length)]);
		}
		for (int i = 0; i < 2; i++) {
			buf.append(NUM_ARRAY[(Math.abs(random.nextInt()) % NUM_ARRAY.length)]);
		}
		return buf.toString();
	}

	  
	public  static String getRandomMsgCode(int size){
		Random random = new Random();
		String result="";
		for(int i=0;i<size;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
	
	/**     
	 * 描述   
	 * @param exhiHist
	 * @return     
	 */
	public static boolean isBlank(String exhiHist) {
		if(exhiHist==null || "".equals(exhiHist)){
			return true;
		}
		return false;
	}

	  
	/**     
	 * 描述   
	 * @param exhiIds
	 * @return     
	 */
	public static boolean isNotBlank(String exhiIds) {
		if(exhiIds!=null && !"".equals(exhiIds)){
			return true;
		}
		return false;
	}
	
	/**
	 * 将对象转换为Long型
	 * @param object
	 */
	public static Long getLongValue(Object object) throws Exception{
		Long value = 0l;
		if(StringUtils.isEmpty(object)){
			return value;
		}
		return Long.valueOf(object.toString());
	}
	
	/**
	 * 将对象转换为BigDecimal型
	 * @param object
	 */
	public static BigDecimal getBigDecimalValue(Object object) throws Exception{
		BigDecimal value = BigDecimal.ZERO;
		if(StringUtils.isEmpty(object)){
			return value;
		}
		return new BigDecimal(object.toString());
	}
	
	/**
	 * 将对象转换为Int型
	 * @param object
	 */
	public static int getIntegerValue(Object object) throws Exception{
		int value = 0;
		if(StringUtils.isEmpty(object)){
			return value;
		}
		return Integer.parseInt(object.toString());
	}
	
	/**
	 * 将对象转换为String型
	 * @param object
	 */
	public static String getStringValue(Object object) throws Exception{
		String value = "";
		if(StringUtils.isEmpty(object)){
			return value;
		}
		return object.toString();
	}
	
	/**
	 * 字符串数组去重
	 * @param strs
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] arrayToWeight(String[] strs){
		String [] rid= new String[]{};
		if(null != strs && strs.length>0){
			List list = Arrays.asList(strs);
			Set set = new HashSet(list);
			rid=(String [])set.toArray(new String[0]);
		}
		return rid;
	}
	
	/**
	 * 获得异常信息
	 * @param e
	 * @return
	 */
	public static String getException(Exception e) {  
        try {  
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);
            pw.close();
            return sw.toString();  
        } catch (Exception e2) {  
            return "bad getException";  
        }
    }
	

	
	/**
	 * 渠道转平台
	 * @param channel
	 * @return
	 */
	public static String transCToP(String channel){
		String platform = "1";
		if("1001".equals(channel) || "1002".equals(channel) || "1003".equals(channel) || "1004".equals(channel) || "1005".equals(channel)){//咖啡烘焙
			platform = "1";
		}else if("1006".equals(channel) || "1007".equals(channel) || "1008".equals(channel) || "1009".equals(channel) || "1010".equals(channel)){//全时生活
			platform = "2";
		}else if("1011".equals(channel) || "1012".equals(channel) || "1013".equals(channel) || "1014".equals(channel) || "1015".equals(channel)){//全时社区
			platform = "3";
		}else if("1016".equals(channel) || "1017".equals(channel) || "1018".equals(channel) || "1019".equals(channel) || "1020".equals(channel)){//全时餐饮
			platform = "4";
		}else if("1021".equals(channel) || "1022".equals(channel) || "1023".equals(channel) || "1024".equals(channel) || "1025".equals(channel)){//撞见旅行
			platform = "5";
		}else{
			platform = "1";
		}
		return platform;
	}
}
