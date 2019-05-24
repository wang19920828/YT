package org.fh.general.ecom.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	/**
	 * 判断是否是正确的IP地址
	 * 
	 * @param ip
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isIp(String ip) {
		if (null == ip || "".equals(ip))
		return false;
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
		+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
		+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
		+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		return ip.matches(regex);
	}
	
	/**
	 * 判断是否是正确的邮箱地址
	 * 
	 * @param email
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
		return false;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return email.matches(regex);
	}
	
	/**
	 * 判断是否含有中文，仅适合中国汉字，不包括标点
	 * @param text
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isChinese(String text) {
		if (null == text || "".equals(text))
		return false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(text);
		return m.find();
	}
	
	/**
	 * 判断是否正整数
	 * 
	 * @param number
	 *            数字
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isNumber(String number) {
		if (null == number || "".equals(number))
		return false;
		String regex = "[0-9]*";
		return number.matches(regex);
	}
	
	/**
	 * 判断几位小数(正数)
	 * 
	 * @param decimal
	 *            数字
	 * @param count
	 *            小数位数
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isDecimal(String decimal, int count) {
		if (null == decimal || "".equals(decimal))
		return false;
		String regex = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0," + count
		+ "})?$";
		return decimal.matches(regex);
	}
	
	/**
	 * 判断是否yyyy-mm-dd
	 * 
	 * @param dates
	 *            日期
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isDate(String dates) {
		if (null == dates || "".equals(dates))
		return false;
		String regex = "\\d{4}-\\d{2}-\\d{2}";
		return dates.matches(regex);
	}
	
	/**
	 * 判断是否yyyy-mm-dd hh:mm:ss
	 * 
	 * @param datetimes
	 *            日期
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isDatetime(String datetimes) {
		if (null == datetimes || "".equals(datetimes))
		return false;
		String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
		return datetimes.matches(regex);
	}
	
	/**
	 * 判断是否是手机号码
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		if (null == phoneNumber || "".equals(phoneNumber))
		return false;
		String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
		return phoneNumber.matches(regex);
	}
	
	/**
	 * 判断是否含有特殊字符
	 * 
	 * @param text
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean hasSpecialChar(String text) {
		if (null == text || "".equals(text))
		return false;
		if (text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
		// 如果不包含特殊字符
		return true;
		}
		return false;
	}
	
	/**
	 * 适应CJK（中日韩）字符集，部分中日韩的字是一样的
	 */
	public static boolean isChinese2(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* 验证输入汉字
	* 
	* @param 待验证的字符串
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean IsChinese(String str) {
	String regex = "^[\u4e00-\u9fa5],{0,}$";
	return match(regex, str);
	}
	
	/**
	* @param regex
	*            正则表达式字符串
	* @param str
	*            要匹配的字符串
	* @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	*/
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是正确的银行卡号
	 * 
	 * @param cardNum
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isCcard(String cardNum) {
		if (null == cardNum || "".equals(cardNum))
		return false;
		String regex = "^(\\d{16}|\\d{17}|\\d{19})$";
		return cardNum.matches(regex);
	}
	public static boolean isCardNo(String cardNo){
		if (null == cardNo || "".equals(cardNo))
			return false;
			String regex = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
			return cardNo.matches(regex);
	}
	public static void main(String[] args) {
		String s = "316466494";
		boolean b = isCardNo(s);
		if(!b){
			System.out.println("error");
		}
	}
	
}
