package org.fh.general.ecom.common.valid;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {
	
	/**
	 * 是否能为空
	 * @return
	 */
	boolean empty() default false;
	
	/**
	 * 取值范围
	 * @return
	 */
	String value() default "";
	
	/**
	 * 最小长度
	 * @return
	 */
	int min() default 0;
	
	/**
	 * 最大长度
	 * @return
	 */
	int max() default 0;
	
	/**
	 * 正则验证
	 * @return
	 */
	RegexType regexType() default RegexType.NONE;
	
	/**
	 * 字段描述
	 * @return
	 */
	String description() default "";
	
}
