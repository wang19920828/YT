package org.fh.general.ecom.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * xml相关的工具类
 * 
 * @author yang.y
 */
public class XmlUtils {
	
	/**
	 * xml字符串转换成bean对象
	 * 
	 * @param xmlStr xml字符串
	 * @param clazz 待转换的class
	 * @return 转换后的对象
	 */
	@SuppressWarnings("rawtypes")
	public static Object xmlStrToBean(String xmlStr, Class clazz) {
		Object obj = null;
		try {
			// 将xml格式的数据转换成Map对象
			Map<String, String> map = xmlStrToMap(xmlStr);
			//将map对象的数据转换成Bean对象
			obj = mapToBean(map, clazz);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	/**
	 * 将Map对象通过反射机制转换成Bean对象
	 * @param map 存放数据的map对象
	 * @param clazz 待转换的class
	 * @return 转换后的Bean对象
	 * @throws Exception 异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object mapToBean(Map<String, String> map, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		if(map != null && map.size() > 0) {
			for(Entry<String, String> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				String setMethodName = "set"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				Class fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
			}
		}
		return obj;
	}
	
	/**
	 * 将<String, Object>Map对象通过反射机制转换成Bean对象
	 * @param map 存放数据的map对象
	 * @param clazz 待转换的class
	 * @return 转换后的Bean对象
	 * @throws Exception 异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object objectMapToBean(Map<String, Object> map, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		if(map != null && map.size() > 0) {
			for(Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				String setMethodName = "set"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				if(field!=null){
					Class fieldTypeClass = field.getType();
					value = convertValType(value, fieldTypeClass);
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				}
			}
		}
		return obj;
	}
	
	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 * @param value Object对象值
	 * @param fieldTypeClass 属性的类型
	 * @return 转换后的值
	 */
	@SuppressWarnings("rawtypes")
	private static Object convertValType(Object value, Class fieldTypeClass) {
		Object retVal = null;
		if(Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if(Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if(Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if(Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 * 
	 * @param clazz 指定的class
	 * @param fieldName 字段名称
	 * @return Field对象
	 */
	@SuppressWarnings("rawtypes")
	private static Field getClassField(Class clazz, String fieldName) {
		if( Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field []declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class superClass = clazz.getSuperclass();
		if(superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	} 
	
	/**
	 * 将xml字符串转成map
	 * 只针对一级的xml
	 * @param xmlStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> conventMap(String xmlStr){
		Map<String,Object> params = new ConcurrentHashMap<String,Object>();
		 SAXBuilder builder = new SAXBuilder();
		 try{
			 Document doc = builder.build(new StringReader(xmlStr));
			 Element root = doc.getRootElement();
			 List<Element> elements= root.getChildren();
			 for(Element el:elements){
				 params.put(el.getName(), el.getText());
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return params;
	}
	
	
	
	/**
	  * 取得xml文件的根节点名称，即消息名称。
	  * 
	  * @param xmlStr
	  *            xml内容
	  * @return String 返回名称
	  */
	 public static String getRootName(String xmlStr) throws Exception {
	  SAXBuilder builder = new SAXBuilder();
	  Document doc = builder.build(new StringReader(xmlStr));
	  Element root = doc.getRootElement();
	  return root.getName();
	 }

	 /**
	  * 把xml文件转换为map形式，其中key为有值的节点名称，并以其所有的祖先节点为前缀，用
	  * "."相连接。如：SubscribeServiceReq.Send_Address.Address_Info.DeviceType
	  * 
	  * @param xmlStr
	  *            xml内容
	  * @return Map 转换为map返回
	  */
	 public static Map<String, String> xmlStrToMap(String xmlStr)
	   throws JDOMException, IOException {
	  Map<String, String> rtnMap = new ConcurrentHashMap<String, String>();
	  SAXBuilder builder = new SAXBuilder();
	  Document doc = builder.build(new StringReader(xmlStr));
	  // 得到根节点
	  Element root = doc.getRootElement();
	  String rootName = root.getName();
	  rtnMap.put("root.name", rootName);
	  // 调用递归函数，得到所有最底层元素的名称和值，加入map中
	  convert(root, rtnMap, rootName);
	  return rtnMap;
	 }

	 /**
	  * 递归函数，找出最下层的节点并加入到map中，由xml2Map方法调用。
	  * 
	  * @param e
	  *            xml节点，包括根节点
	  * @param map
	  *            目标map
	  * @param lastname
	  *            从根节点到上一级节点名称连接的字串
	  */
	 @SuppressWarnings("rawtypes")
	public static void convert(Element e, Map<String, String> map,
	   String lastname) {
	  if (e.getAttributes().size() > 0) {
	   Iterator it_attr = e.getAttributes().iterator();
	   while (it_attr.hasNext()) {
	    Attribute attribute = (Attribute) it_attr.next();
	    String attrname = attribute.getName();
	    String attrvalue = e.getAttributeValue(attrname);
	    //map.put( attrname, attrvalue);
	    map.put(lastname + "." + attrname, attrvalue); //key 根据根节点 进行生成
	   }
	  }
	  List children = e.getChildren();
	  Iterator it = children.iterator();
	  while (it.hasNext()) {
	   Element child = (Element) it.next();
	   /*String name = lastname + "." + child.getName();*/
	   String name = child.getName();
	   // 如果有子节点，则递归调用
	   if (child.getChildren().size() > 0) {
	    convert(child, map,  lastname + "." + child.getName());
	   } else {
	    // 如果没有子节点，则把值加入map
	    map.put(name, child.getText());
	    // 如果该节点有属性，则把所有的属性值也加入map
	    if (child.getAttributes().size() > 0) {
	     Iterator attr = child.getAttributes().iterator();
	     while (attr.hasNext()) {
	      Attribute attribute = (Attribute) attr.next();
	      String attrname = attribute.getName();
	      String attrvalue = child.getAttributeValue(attrname);
	      map.put(lastname + "." + child.getName() + "." + attrname, attrvalue);
	      //map.put( attrname, attrvalue);
	     }
	    }
	   }
	  }
	 }

	 /**
	  * 把xml文件转换为list形式，其中每个元素是一个map，map中的key为有值的节点名称，并以其所有的祖先节点为前缀，用
	  * "."相连接。如：SubscribeServiceReq.Send_Address.Address_Info.DeviceType
	  * 
	  * @param xmlStr
	  *            xml内容
	  * @return Map 转换为map返回
	  */
	 public static List<Map<String, String>> xml2List(String xmlStr)
	   throws JDOMException, IOException {
	  List<Map<String, String>> rtnList = new ArrayList<Map<String, String>>();
	  Map<String, String> rtnMap = new ConcurrentHashMap<String, String>();
	  SAXBuilder builder = new SAXBuilder();
	  Document doc = builder.build(new StringReader(xmlStr));
	  // 得到根节点
	  Element root = doc.getRootElement();
	  String rootName = root.getName();
	  rtnMap.put("root.name", rootName);
	  // 调用递归函数，得到所有最底层元素的名称和值，加入map中
	  convert2List(root, rtnMap, rootName, rtnList);
	  if (rtnList.size() == 0)
	   rtnList.add(rtnMap);
	  return rtnList;
	 }

	 /**
	  * 递归函数，找出最下层的节点并加入到map中，如果有相同的节点，则加入list中， 由xml2List方法调用。
	  * 
	  * @param e
	  *            xml节点，包括根节点
	  * @param map
	  *            目标map
	  * @param lastname
	  *            从根节点到上一级节点名称连接的字串
	  * @param list
	  *            相同节点生成map放入list中
	  */
	 @SuppressWarnings("rawtypes")
	public static void convert2List(Element e, Map<String, String> map,
	   String lastname, List<Map<String, String>> list) {
	  if (e.getAttributes().size() > 0) {
	   Iterator it_attr = e.getAttributes().iterator();
	   while (it_attr.hasNext()) {
	    Attribute attribute = (Attribute) it_attr.next();
	    String attrname = attribute.getName();
	    String attrvalue = e.getAttributeValue(attrname);
	    map.put(attrname, attrvalue);
	   }
	  }
	  List children = e.getChildren();
	  Iterator it = children.iterator();
	  while (it.hasNext()) {
	   Element child = (Element) it.next();
	   String name = lastname + "." + child.getName();
	   // 如果有子节点，则递归调用
	   if (child.getChildren().size() > 0) {
	    convert(child, map, name);
	   } else {
	    // 如果没有子节点，则把值加入map
	    map.put(name, child.getText());
	    // 如果该节点有属性，则把所有的属性值也加入map
	    if (child.getAttributes().size() > 0) {
	     Iterator attr = child.getAttributes().iterator();
	     while (attr.hasNext()) {
	      Attribute attribute = (Attribute) attr.next();
	      String attrname = attribute.getName();
	      String attrvalue = child.getAttributeValue(attrname);
	      map.put(name + "." + attrname, attrvalue);
	     }
	    }
	   }
	   // 如果有相同节点，则加入list中，不考虑子节点中又有相同节点的情况
	   if (e.getChildren(child.getName()).size() > 1) {
	    Map<String, String> aMap = new ConcurrentHashMap<String, String>();
	    aMap.putAll(map);
	    list.add(aMap);
	    map = new ConcurrentHashMap<String, String>();
	    map.put("root.name", aMap.get("root.name"));
	   }
	  }
	 }

	 public static String ArrayToXml(Map<String, String> arr) {
        String xml = "<xml>";

        Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (IsNumeric(val)) {
                xml += "<" + key + ">" + val + "</" + key + ">";

            } else
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
        }

        xml += "</xml>";
        return xml;
    }

	 public static boolean IsNumeric(String str) {
        if (str.matches("\\d *")) {
            return true;
        } else {
            return false;
        }
    }
}
