package org.fh.general.ecom.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParseParamUtil {

	
	public static Map<String, Object> getUrlParam(String param) {
		Map<String, Object> mapRequest = new ConcurrentHashMap<String, Object>();

		String[] arrSplit = null;
		// 每个键值为一组 www.2cto.com
		arrSplit = param.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
}
