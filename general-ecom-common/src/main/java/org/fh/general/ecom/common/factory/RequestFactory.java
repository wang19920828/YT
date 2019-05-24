package org.fh.general.ecom.common.factory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestFactory {

	/**
	 * 根据当前请求获取请求参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<?> paramNames = request.getParameterNames();
		try {
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue;
					if ("GET".equals(request.getMethod().toString())) {
						paramValue = new String(paramValues[0].getBytes("iso-8859-1"), "utf-8");
					} else {
						paramValue = paramValues[0];
					}
					if (paramValue.length() != 0) {
						params.put(paramName, paramValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	public static SortedMap<String, Object> getParamMap(HttpServletRequest request){
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		Enumeration<?> paramNames = request.getParameterNames();
		try {
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue;
					if ("GET".equals(request.getMethod().toString())) {
						paramValue = new String(paramValues[0].getBytes("iso-8859-1"), "utf-8");
					} else {
						paramValue = paramValues[0];
					}
					if (paramValue.length() != 0) {
						params.put(paramName, paramValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
