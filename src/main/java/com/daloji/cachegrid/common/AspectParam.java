package com.daloji.cachegrid.common;

import static java.util.Objects.nonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.daloji.cachegrid.aspectj.Cache;

public class AspectParam<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Class<T>[] clazzParam;

	private Class<T> clazzReturn;

	private Object[] paramValue;
	
	private String nameCache;

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

	public void setParamJointPoint(ProceedingJoinPoint jointpoint) {
		Signature signature = jointpoint.getStaticPart().getSignature();
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			clazzReturn = ms.getReturnType();
			clazzParam = ms.getParameterTypes();
			paramValue = jointpoint.getArgs();
			nameCache = ms.getMethod().getAnnotation(Cache.class).engineName();
			if(nonNull(paramValue) && paramValue.length>0) {
				byte[] data = null;
				for(Object obj:paramValue) {
					System.out.println(obj);
					byte[] dataArgs = convertToBytes(obj);
					if(nonNull(data)) {
						byte[] tmp =  new byte[data.length + dataArgs.length];
						System.arraycopy(data, 0, tmp, 0, data.length);
						System.arraycopy(dataArgs, 0, tmp, data.length, dataArgs.length);
						data=tmp;
					}else {
						data =dataArgs;
					}
				}

				if(nonNull(data)) {
					System.out.println(bytesToHex(data));
				}

			}
			
			
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
