package org.fh.general.ecom.common.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;

/**
 * @Description JSON的工具类
 * @author liuzhaoq
 * @Create at 2012-11-16 上午11:19:14
 * @modify 上午11:19:14
 */
public class JsonMapper {

	private ObjectMapper mapper;

	public JsonMapper(Inclusion inclusion) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 禁止使用int代表Enum的order()來反序列化Enum,非常危险
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}

	/**
	 * 创建输出全部属性到Json字符串的Mapper.
	 */
	public static JsonMapper buildNormalMapper() {
		return new JsonMapper(Inclusion.ALWAYS);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Mapper.
	 */
	public static JsonMapper buildNonNullMapper() {
		return new JsonMapper(Inclusion.NON_NULL);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper.
	 */
	public static JsonMapper buildNonDefaultMapper() {
		return new JsonMapper(Inclusion.NON_DEFAULT);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需读取集合如List/Map, 且不是List<String>这种简单类型时使用如下语句,使用后面的函数.
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需读取集合如List/Map, 且不是List<String>時, 先用constructParametricType(List.class,MyBean.class)构造出JavaTeype,再調用本函数.
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 构造泛型的Type如List<MyBean>, Map<String,MyBean>
	 */
	public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	/**
	 * 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 当JSON里只含有Bean的部分属性時，更新一个已存在Bean，只覆部分的属性.
	 */
	@SuppressWarnings("unchecked")
	public <T> T update(T object, String jsonString) {
		try {
			return (T) mapper.updatingReader(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 输出JSONP格式数据.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函数来读写Enum, 为False时时使用Enum的name()函数来读写Enum, 默认为False. 注意本函数一定要在Mapper创建后, 所有的读写动作之前調用.
	 */
	public void setEnumUseToString(boolean value) {
		mapper.getSerializationConfig().set(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
		mapper.getDeserializationConfig().set(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
	
	public static JsonMapper buildMapper(){
		return new JsonMapper();
	}
	
	public JsonMapper()  {  
		mapper = new ObjectMapper();
        // 允许单引号   
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);   
        // 字段和值都加引号   
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);   
        // 数字也加引号   
		mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);   
		mapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);   
        // 空值处理为空串   
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()   
        {   
            @Override   
            public void serialize(   
                    Object value,   
                    JsonGenerator jg,   
                    SerializerProvider sp) throws IOException, JsonProcessingException   
            {   
                jg.writeString("");   
            }   
        });   
    }   

}
