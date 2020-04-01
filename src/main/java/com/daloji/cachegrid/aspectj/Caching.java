package com.daloji.cachegrid.aspectj;


import static java.util.Objects.nonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.daloji.cachegrid.CacheManager;

@Aspect
public class Caching {

	CacheManager cacheManager = CacheManager.getInstance() ;

	@Around("execution(* *(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)")
	public Object cacheable(ProceedingJoinPoint joinPoint) throws Throwable
	{
		System.out.println("icici");
		Signature signature = joinPoint.getStaticPart().getSignature();
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			String[] params = ms.getParameterNames();
			for (String param : params) {
				//		System.out.println(ms.getReturnType().getName() + " : " + ms.getParameterTypes()[0].getName() + " : "+joinPoint.getArgs()[0]);
				// here how do i get parameter value using param ?
			}
		}

		if(nonNull(joinPoint.getArgs()) && joinPoint.getArgs().length>0) {
			byte[] data = null;
			for(Object obj:joinPoint.getArgs()) {
		System.out.println(obj);
				byte[] dataArgs = toByteArray(obj);
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

		//thisJoinPoint.
		joinPoint.proceed();	
		return null;	
	}


	public static String bytesToHex(byte[] bytes) {
		final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	//@Around("execution(@com.daloji.cachegrid.aspectj.Cache * *(..))")
	public void logContinueProcess(ProceedingJoinPoint thisJoinPoint) throws Throwable
	{
		System.out.println("LALLALAL");
		thisJoinPoint.proceed();

	}

	//@Before("execution(* *.*(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)")
	public int logContinue(ProceedingJoinPoint thisJoinPoint) throws Throwable
	{
		System.out.println("LALLALAL  pppppp");
		thisJoinPoint.proceed();
		return 1;

	}



	private  byte[] toByteArray(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}



	public  byte[] Sha256(byte[] input) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.update(input, 0, input.length);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}

}