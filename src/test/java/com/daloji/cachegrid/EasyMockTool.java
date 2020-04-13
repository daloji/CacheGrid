package com.daloji.cachegrid;

import java.lang.reflect.Field;

public class EasyMockTool {

	public static void setInaccessibleFieldValue(final Object obj_p, final String fieldName_p, final Object value_p) throws NoSuchFieldException, IllegalAccessException
	{
		final Field field = getField(obj_p.getClass(), fieldName_p);
		field.setAccessible(true);
		field.set(obj_p, value_p);
	}

	public static Field getField(final Class<?> clazz_p, final String fieldName_p) throws NoSuchFieldException
	{
		try
		{
			return clazz_p.getDeclaredField(fieldName_p);
		}
		catch (NoSuchFieldException exception)
		{
			final Class<?> superClass = clazz_p.getSuperclass();
			if (null == superClass)
			{
				throw exception;
			}
			return getField(superClass, fieldName_p);
		}
	}

	
}
