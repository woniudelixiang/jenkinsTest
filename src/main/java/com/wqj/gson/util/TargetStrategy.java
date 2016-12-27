/**
 * 
 */
package com.wqj.gson.util;

import org.apache.commons.lang.ArrayUtils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class TargetStrategy implements ExclusionStrategy {


	private Class<?> target;

	private String[] fields;

	private Class<?>[] clazz;

	private boolean reverse;

	public TargetStrategy(Class<?> target) {

		super();

		this.target = target;

	}

	@Override
	public boolean shouldSkipClass(Class<?> class1) {

		return false;

	}

	@Override
	public boolean shouldSkipField(FieldAttributes fieldattributes) {

		Class<?> owner = fieldattributes.getDeclaringClass();

		Class<?> c = fieldattributes.getDeclaredClass();

		String f = fieldattributes.getName();

		boolean isSkip = false;

		if (owner == target) {

			if (ArrayUtils.contains(fields, f)) {

				//log.debug("fitler field:{} for class:{}", f, owner);

				isSkip = true;

			}

			if (ArrayUtils.contains(clazz, c)) {

				//log.debug("fitler class:{} for class:{}", c, owner);

				isSkip = true;

			}

			if (reverse) {

				isSkip = !isSkip;

			}

		}

		return isSkip;

	}

	public void setFields(String[] fields) {

		this.fields = fields;

	}

	public void setClazz(Class<?>[] clazz) {

		this.clazz = clazz;

	}

	public void setReverse(boolean reverse) {

		this.reverse = reverse;

	}
}