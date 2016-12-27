package com.wqj.common.util;

import java.util.Collection;
import org.springframework.util.Assert;

public class AssertUtil {
	
	/**
	 * 当 object为  null 时抛出异常
	 * @param object
	 */
	public static void notNull(Object object){
		Assert.notNull(object);
	}
	
	/**
	 * 当 object为  null 时抛出异常
	 * @param object
	 * @param message  异常信息
	 */
	public static void notNull(Object object, String message){
		Assert.notNull(object, message);
	}
	
	/**
	 * 当 object不为  null 时抛出异常
	 * @param object
	 */
	public static void isNull(Object object){
		Assert.isNull(object);
	}
	
	/**
	 * 当 object不为  null 时抛出异常
	 * @param object
	 * @param message  异常信息
	 */
	public static void isNull(Object object, String message){
		Assert.isNull(object, message);
	}
	
	/**
	 * 当 expression 不为 true 抛出异常
	 * @param expression
	 */
	public static void isTrue(boolean expression) {
		Assert.isTrue(expression);
	}
	
	/**
	 * 当 expression 不为 true 抛出异常
	 * @param expression
	 * @param message
	 */
	public static void isTrue(boolean expression, String message) {
		Assert.isTrue(expression, message);
	}
	
	/**
	 * 当集合未包含元素时抛出异常
	 * @param collection
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Collection collection) {
		Assert.notEmpty(collection);
	}

	/**
	 * 当集合未包含元素时抛出异常
	 * @param collection
	 * @param message
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty( Collection collection, String message) {
		Assert.notEmpty(collection, message);
	}
	
	/**
	 * 当集合未包含元素时抛出异常
	 * @param collection
	 */
	public static void notEmpty(Object[] array) {
		Assert.notEmpty(array);
	}

	/**
	 * 当集合未包含元素时抛出异常
	 * @param collection
	 * @param message
	 */
	public static void notEmpty(Object[] array, String message) {
		Assert.notEmpty(array, message);
	}
	
	/**
	 * 当text为 null 或长度为 0 时抛出异常
	 * @param text
	 */
	public static void hasLength(String text){
		Assert.hasLength(text);
	}
	
	/**
	 * 当text为 null 或长度为 0 时抛出异常
	 * @param text
	 * @param message
	 */
	public static void hasLength(String text, String message){
		Assert.hasLength(text, message);
	}
	
	/**
	 * text不为 null 且必须至少包含一个非空格的字符，否则抛出异常
	 * @param text
	 */
	public static void hasText(String text){
		Assert.hasText(text);
	}
	
	/**
	 * text不为 null 且必须至少包含一个非空格的字符，否则抛出异常
	 * @param text
	 * @param message
	 */
	public static void hasText(String text, String message){
		Assert.hasText(text, message);
	}
	
	/**
	 * 如果 obj 不能被正确造型为 clazz 指定的类将抛出异常
	 * @param clazz
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	public static void isInstanceOf(Class clazz, Object obj) {
		Assert.isInstanceOf(clazz, obj);
	}
	
	/**
	 * 如果 obj 不能被正确造型为 clazz 指定的类将抛出异常
	 * @param type
	 * @param obj
	 * @param message
	 */
	@SuppressWarnings("rawtypes")
	public static void isInstanceOf(Class type, Object obj, String message) {
		Assert.isInstanceOf(type, obj, message);
	}
	
	/**
	 * subType 必须可以按类型匹配于 superType，否则将抛出异常；
	 * @param superType
	 * @param subType
	 */
	@SuppressWarnings("rawtypes")
	public static void isAssignable(Class superType, Class subType) {
		Assert.isAssignable(superType, subType);
	}
	
	/**
	 * subType 必须可以按类型匹配于 superType，否则将抛出异常；
	 * @param superType
	 * @param subType
	 * @param message
	 */
	@SuppressWarnings("rawtypes")
	public static void isAssignable(Class superType, Class subType, String message) {
		Assert.isAssignable(superType, subType, message);
	}
}
