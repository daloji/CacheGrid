package com.daloji.cachegrid.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.daloji.cachegrid.aspectj.Cache;

/**
 * Object param for all method
 * @author daloji
 *
 * @param <T>
 */
public class AspectParam<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Class<T>[] clazzParam;

	private Class<T> clazzReturn;

	private Object[] paramValue;

	private String nameCache;

	private String methodeName;

	public String getMethodeName() {
		return methodeName;
	}

	public void setMethodeName(String methodeName) {
		this.methodeName = methodeName;
	}

	public Class<T>[] getClazzParam() {
		return clazzParam;
	}

	public void setClazzParam(Class<T>[] clazzParam) {
		this.clazzParam = clazzParam;
	}

	public Class<T> getClazzReturn() {
		return clazzReturn;
	}

	public void setClazzReturn(Class<T> clazzReturn) {
		this.clazzReturn = clazzReturn;
	}

	public Object[] getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object[] paramValue) {
		this.paramValue = paramValue;
	}


	public String getNameCache() {
		return nameCache;
	}

	public void setNameCache(String nameCache) {
		this.nameCache = nameCache;
	}

	public void setParamJointPoint(ProceedingJoinPoint proceedingJointpoint) {
		Signature signature = proceedingJointpoint.getStaticPart().getSignature();
		setfillSignature(signature);
		paramValue = proceedingJointpoint.getArgs();

	}


	public void setParamJointPoint(JoinPoint jointpoint) {
		Signature signature = jointpoint.getStaticPart().getSignature();
		setfillSignature(signature);
		paramValue = jointpoint.getArgs();
	}

	private void setfillSignature(final Signature signature) {
		if (signature instanceof MethodSignature) {
			MethodSignature methode = (MethodSignature) signature;
			clazzReturn = methode.getReturnType();
			clazzParam = methode.getParameterTypes();
			methodeName = methode.getName();
			nameCache = methode.getMethod().getAnnotation(Cache.class).engineName();
		}
	}

	/**
	 * Conversion d'un objet en byte Array
	 * @param object
	 * @return
	 */
	public  byte[] convertToBytes(Object object)  {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutput out = new ObjectOutputStream(bos)) {
			out.writeObject(object);
			return bos.toByteArray(); 
		} catch (IOException e) {
			return null;
		} 
	}

	public  String bytesToHex(byte[] bytes) {
		char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

}
