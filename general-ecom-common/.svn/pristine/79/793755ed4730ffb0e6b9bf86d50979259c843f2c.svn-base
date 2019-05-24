package org.fh.general.ecom.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;


@SuppressWarnings("restriction")
public class PasswordUtils {
	/**
	 * md5加密密码
	 * @param password
	 * @return
	 */
	public static String doubleMd5(String password)
	{
		if (StringUtils.isNotEmpty(password))
		{
			return md5String(md5String(password));
		}
		return password;
	}
	
	/**
	 * md5加密key
	 * @param password
	 * @return
	 */
	public static String Md5(String password)
	{
		if (StringUtils.isNotEmpty(password))
		{
			return md5String(password);
		}
		return password;
	}
	
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

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		}

		return s1.equalsIgnoreCase(s2);
	}
	
	public static String nullSafe(String s) {
		return s == null ? "":s;
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
		if (StringUtils.isEmpty(s)) {
			return 0;
		}

		try {
			return s.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
		}
		return s.getBytes().length;
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
		if (StringUtils.isEmpty(s)) {
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
		if (StringUtils.isEmpty(s)) {
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
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String pwd = PasswordUtils.doubleMd5("1qaz2wsx");
		System.out.println(String.valueOf(Integer.valueOf((int) Math.ceil(Math.random()*500000+500000))));
	}
}
