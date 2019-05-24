package org.fh.general.ecom.common.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
	
	/**
     * 正则表达式：验证纯数字
     */
    public static final String REGEX_NUMERIC = "[0-9]*";
    
    /**
     * 生成随机字符串随机范围
     */
    public static final String RANDOM_RANGE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * 生成随机字符串随机范围
     */
    public static final String RANDOM_RANGE_SMALL = "0123456789abcdefghijklmnopqrstuvwxyz";
    
	/**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
 
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";
    
    /**
     * 验证带区号的电话号码
     */
    public static final String REGEX_AREAPHONE = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    
    /**
     * 验证没区号的电话号码
     */
    public static final String REGEX_PHONE = "^[1-9]{1}[0-9]{5,8}$";
    
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 正则表达式：验证金额
     */
    public static final String REGEX_AMOUNT = "-?[0-9]*(\\.[\\d]{1,2})?";

    /**
     * 正则表达式：验证税率
     */
    public static final String REGEX_RATE = "[0-9]{1,2}(\\.[\\d]{1,2})?%";
    public static final String REGEX_RATE2 = "0\\.\\d{1,4}?$";
    
    /**
     * 验证纯数字
     * @param number
     * @return
     */
    public static boolean isNumeric(String number){ 
    	return isCorrect(REGEX_NUMERIC,number);
    	}
    
	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String mobiles) {
		return isCorrect(REGEX_MOBILE,mobiles);
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String phone) {
		if (phone.length() > 9) {
			return isCorrect(REGEX_AREAPHONE,phone);
		} else {
			return isCorrect(REGEX_PHONE,phone);
		}
	}

	// 验证身份证号码
	/*private boolean idCardNumber(String number) {
		String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
		return isCorrect(rgx, number);
	}*/

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		return isCorrect(REGEX_EMAIL, email);
	}

	
	// 正则验证
	public static boolean isCorrect(String rgx, String res) {
		Pattern p = Pattern.compile(rgx);
		Matcher m = p.matcher(res);
		return m.matches();
	}
	//金额验证
	public static boolean isAmount(String value){
		return isCorrect(REGEX_AMOUNT, value);
	}
    public static boolean checkDate(String content) {
        Pattern p = Pattern
                .compile("^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        boolean result = p.matcher(content).matches();
        if(result){
        	Date date = DateUtils.getDate(content);
            String contentType = DateUtils.formatDate(date, null);
            if(!contentType.equals(content)){
            	result = false;
            }
        }
        return result;
    }
    
    
    public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         try {
        	 // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
         } catch (Exception e) {
            // e.printStackTrace();
  // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
             convertSuccess=false;
         } 
         return convertSuccess;
  }
    
    public static boolean isInt(String value){
    	return isCorrect("^\\+?[1-9][0-9]*$", value);
    }
    public static boolean isNum(String value){
    	Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
    	Matcher isNum = pattern.matcher(value);
    	if (!isNum.matches()) {
    	return false;
    	}
    	return true;
    }
    public static boolean rateValid(String value){
    	return isCorrect(REGEX_RATE, value) | isCorrect(REGEX_RATE2, value);
    }
    
    
    public static void main(String[] args) {
    	System.out.println(new BigDecimal("0.3"));
    	
        }
    
    
}
