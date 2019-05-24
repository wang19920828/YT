package org.fh.general.ecom.common.valid;

import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

public class VateFactory {

	public static void valid(Object object) throws Exception {
		// 获取object的类型
		Class<? extends Object> clazz = object.getClass();
		// 获取该类型声明的成员
		Field[] fields = clazz.getDeclaredFields();
		// 遍历属性
		for (Field field : fields) {
			// 对于private私有化的成员变量，通过setAccessible来修改器访问权限
			field.setAccessible(true);
			validate(field, object);
			// 重新设置会私有权限
			field.setAccessible(false);
		}
	}

	public static void validate(Field field, Object object) throws Exception {
		// 获取对象的成员的注解信息
		Check check = field.getAnnotation(Check.class);
		Object value = field.get(object);
		if(value == null){
			value = "";
		}
		if (check == null)
			return;
		String description = check.description().equals("") ? field.getName()
				: check.description();
		/************* 注解解析工作开始 ******************/
		if (!check.empty()) {
			if (value == null || StringUtils.isEmpty(value)) {
				throw new Exception("请填写/选择"+description);
			}
		}

		if (value != null || !StringUtils.isEmpty(value)) {
			if (value.toString().length() > check.max() && check.max() != 0) {
				throw new Exception(description + "长度不能超过" + check.max());
			}

			if (value.toString().length() < check.min() && check.min() != 0) {
				throw new Exception(description + "长度不能小于" + check.min());
			}
		}

		if(!StringUtils.isEmpty(check.value())){
			if(check.value().indexOf(value.toString())<=-1){
				throw new Exception(description + "必须在" + check.value()+"之中");
			}
		}
		if (check.regexType() != RegexType.NONE && !StringUtils.isEmpty(value)) {
			switch (check.regexType()) {
			case NONE:
				break;
			case SPECIALCHAR:
				if (!VateMethod.hasSpecialChar(value.toString())) {
					throw new Exception(description + "不能含有特殊字符");
				}
				break;
			case CHINESE:
				if (!VateMethod.IsChinese(value.toString())) {
					throw new Exception(description + "有非中文字符");
				}
				break;
			case EMAIL:
				if (!VateMethod.isEmail(value.toString())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case IP:
				if (!VateMethod.isIp(value.toString())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case NUMBER:
				if (!VateMethod.isNumber(value.toString())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case DECIMAL:
				if (!VateMethod.isDecimal(value.toString(), 2)) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case DATE:
				if (!VateMethod.isDate(value.toString())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case DATETIME:
				if (!VateMethod.isDatetime(value.toString())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			case PHONENUMBER:
				if (!VateMethod.isPhoneNumber(value.toString().trim())) {
					throw new Exception(description + "格式不正确");
				}
				break;
			default:
				break;
			}

		}
		/************* 注解解析工作结束 ******************/
	}

	public static  Object setValToObj(Object entityName, Map<String, String> para){
		 try {
			 Class c = entityName.getClass();
			 // 获得对象属性
			 Field field[] = c.getDeclaredFields();
			 for (Field f : field) {
				 try {
					 if("serialVersionUID".equals(f.getName())){
						 continue;
					 }
					 PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
					 Method writeMethod = pd.getWriteMethod();
					 if(null != para.get(f.getName()))
						 writeMethod.invoke(entityName, ConvertType(f.getType().toString(),para.get(f.getName())));
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
	  * 描述   类型转换
	  * @param type
	  * @param value
	  * @return
	  */
	 public static Object ConvertType(String type,String value){
	 	if(type.endsWith("int") || type.endsWith("Integer")){
	 		if(StringUtils.isEmpty(value)){
	 			return 0;
	 		}else{
	 			return Integer.valueOf(value.toString());
	 		}
	 	}else if(type.endsWith("long") ||type.endsWith("Long")){
	 		if(StringUtils.isEmpty(value)){
	 			return 0l;
	 		}else{
	 			return Long.valueOf(value.toString());
	 		}

	 	}else if(type.endsWith("BigDecimal")){
	 		if(StringUtils.isEmpty(value)){
	 			return new BigDecimal(0.00);
	 		}else{
	 			return new BigDecimal(value.toString());
	 		}

	 	}else{
	 		return value.toString();
	 	}
	 }

}