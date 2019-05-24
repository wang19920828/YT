package org.fh.general.ecom.common.utils;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ObjectUtils {
	public static final Object NULL = null;

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static boolean notNull(Object o) {
		return !isNull(o);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (isNull(o)) {
			return true;
		}

		if ((o instanceof String)) {
			return "".equals(o.toString());
		}
		if ((o instanceof Collection)) {
			return ((Collection) o).isEmpty();
		}
		if ((o instanceof Map)) {
			return ((Map) o).isEmpty();
		}
		if (o.getClass().isArray()) {
			return Array.getLength(o) == 0;
		}

		return true;
	}

	public static boolean notEmpty(Object o) {
		return !isEmpty(o);
	}

	public static <T> T nullSafe(T actual, T safe) {
		return actual == null ? safe : actual;
	}

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		} 
		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			if (map.containsKey(field.getName())) {
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		}
		return obj;
	}

	@SuppressWarnings("rawtypes")
	public static Object setValToObj(Object entityName, Map<String, String> para) {
		try {
			Class c = entityName.getClass();
			// 获得对象属性
			Field field[] = c.getDeclaredFields();
			for (Field f : field) {
				try {
					if ("serialVersionUID".equals(f.getName())) {
						continue;
					}
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
					Method writeMethod = pd.getWriteMethod();
					if (null != para.get(f.getName()))
						writeMethod.invoke(entityName, ConvertType(f.getType().toString(), para.get(f.getName())));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityName;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static SortedMap convertBean(Object bean) {
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		try {
			if (bean != null) {
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					if (key.compareToIgnoreCase("class") == 0) {
						continue;
					}
					Method getter = property.getReadMethod();
					Object value = getter != null ? getter.invoke(bean) : null;
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 描述 类型转换
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static Object ConvertType(String type, String value) {
		if (type.endsWith("int") || type.endsWith("Integer")) {
			if (StringUtils.isEmpty(value)) {
				return 0;
			} else {
				return Integer.valueOf(value.toString());
			}
		} else if (type.endsWith("long") || type.endsWith("Long")) {
			if (StringUtils.isEmpty(value)) {
				return 0l;
			} else {
				return Long.valueOf(value.toString());
			}

		} else if (type.endsWith("BigDecimal")) {
			if (StringUtils.isEmpty(value)) {
				return new BigDecimal(0.00);
			} else {
				return new BigDecimal(value.toString());
			}

		} else {
			return value.toString();
		}
	}

	public static Map<String, String> mapToString(Map<String, Object> map) {
		Map<String, String> newMap = new ConcurrentHashMap<String, String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof String) {
				newMap.put(entry.getKey(), (String) entry.getValue());
			}
		}
		return newMap;
	}

	/**
	 * 将分转换成元
	 * 
	 * @param obj
	 * @return
	 */
	public static BigDecimal convertYuan(Object obj) {
		float num = Float.valueOf(obj.toString()) / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(num);
		return new BigDecimal(str);
	}

	/**
	 * 将元转换成分
	 * 
	 * @param dec
	 * @return
	 */
	public static int convertCent(BigDecimal dec) {
		if (dec.compareTo(BigDecimal.ZERO) == 0) {
			return 0;
		}
		// 乘以100(单位：分)
		BigDecimal amt = dec.multiply(new BigDecimal(100));
		return amt.intValue();

	}

	/**
	 * 将元转换成分
	 * 
	 * @param dec
	 * @return
	 */
	public static BigDecimal yuantofen(BigDecimal dec) {
		if (dec.compareTo(BigDecimal.ZERO) == 0) {
			return new BigDecimal(0);
		}
		// 乘以100(单位：分)
		return dec.multiply(new BigDecimal(100));

	}

	/**
	 * 定义分割常量 （#在集合中的含义是每个元素的分割，|主要用于map类型的集合用于key与value中的分割）
	 */
	private static final String SEP1 = "=";
	private static final String SEP2 = "&";

	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :需要转换的Map
	 * @return String转换后的字符串
	 */
	public static String MapToString(Map<?, ?> map) {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof List<?>) {
				sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
				sb.append(SEP2);
			} else if (value instanceof Map<?, ?>) {
				sb.append(key.toString() + SEP1 + MapToString((Map<?, ?>) value));
				sb.append(SEP2);
			} else {
				sb.append(key.toString() + SEP1 + value.toString());
				sb.append(SEP2);
			}
		}
		/* return "M" + EspUtils.EncodeBase64(sb.toString()); */
		return "#" + sb.toString();
	}

	/**
	 * String转换Map
	 * 
	 * @param mapText
	 *            :需要转换的字符串
	 * @param //KeySeparator
	 *            :字符串中的分隔符每一个key与value中的分割
	 * @param //ElementSeparator
	 *            :字符串中每个元素的分割
	 * @return Map<?,?>
	 */
	public static Map<String, Object> StringToMap(String mapText) {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);

		/*
		 * mapText = EspUtils.DecodeBase64(mapText); mapText =mapText;
		 */

		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		String[] text = mapText.split("\\" + SEP2); // 转换为数组
		for (String str : text) {
			String[] keyText = str.split(SEP1); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			String key = keyText[0]; // key
			if (keyText.length > 1) {
				String value = keyText[1]; // value
				if (value.charAt(0) == '#') {
					Map<?, ?> map1 = StringToMap(value);
					map.put(key, map1);
				} else if (value.charAt(0) == 'L') {
					List<?> list = StringToList(value);
					map.put(key, list);
				} else {
					map.put(key, value);
				}
			}
		}
		return map;
	}

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	public static String ListToString(List<?> list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				// 如果值是list类型则调用自己
				if (list.get(i) instanceof List) {
					sb.append(ListToString((List<?>) list.get(i)));
					sb.append(SEP1);
				} else if (list.get(i) instanceof Map) {
					sb.append(MapToString((Map<?, ?>) list.get(i)));
					sb.append(SEP1);
				} else {
					sb.append(list.get(i));
					sb.append(SEP1);
				}
			}
		}
		/* return "L" + EspUtils.EncodeBase64(sb.toString()); */
		return "L" + sb.toString();
	}

	/**
	 * String转换List
	 * 
	 * @param listText
	 *            :需要转换的文本
	 * @return List<?>
	 */
	public static List<Object> StringToList(String listText) {
		if (listText == null || listText.equals("")) {
			return null;
		}
		listText = listText.substring(1);

		/*
		 * listText = EspUtils.DecodeBase64(listText); listText = listText;
		 */
		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split(SEP1);
		for (String str : text) {
			if (str.charAt(0) == 'M') {
				Map<?, ?> map = StringToMap(str);
				list.add(map);
			} else if (str.charAt(0) == 'L') {
				List<?> lists = StringToList(str);
				list.add(lists);
			} else {
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 描述 转换Map<String, Object>===》Map<String, String>
	 * 
	 * @param mapObj
	 * @return
	 */
	public static Map<String, String> ConvertMapObjToMapString(Map<String, Object> mapObj) {
		Map<String, String> mapStr = new ConcurrentHashMap<String, String>();
		Iterator<Entry<String, Object>> it = mapObj.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value != null) {
				mapStr.put(key, value.toString());
			}
		}
		return mapStr;
	}

	/**
	 * 描述 转换Map<String, Object>===》Map<String, String>
	 * 
	 * @param mapStr
	 * @return
	 */
	public static Map<String, Object> ConvertMapStringToMapObj(Map<String, String> mapStr) {
		Map<String, Object> mapObj = new ConcurrentHashMap<String, Object>();
		Iterator<Entry<String, String>> it = mapStr.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			mapObj.put(key, value);
		}
		return mapObj;
	}

	public static BigDecimal formateRate(String value) {
		return new BigDecimal(value).divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> toHashMap(String str) {
		com.alibaba.fastjson.JSONObject jasonObj = com.alibaba.fastjson.JSONObject.parseObject(str);
		Map<String, Object> data = new ConcurrentHashMap<String, Object>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(jasonObj);
		Iterator it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 描述 list转String
	 * 
	 * @param stringList
	 * @return
	 */
	public static String converListToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * 描述 随机生成10位数
	 * 
	 * @return
	 */
	public static String suiJiRandom() {
		int ss = (int) ((Math.random() * 9 + 1) * 1000000000);
		System.out.println(ss);
		return ss + "";
	}

	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}


	public static Map<String, Object> beanToMongoMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					if(propertyUtilsBean.getNestedProperty(obj, name)!=null && !"".equals(propertyUtilsBean.getNestedProperty(obj, name))) {
						params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
}