package com.daloji.cachegrid.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.MockStrict;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.daloji.cachegrid.CacheManager;
import com.daloji.cachegrid.aspectj.Caching;

import javassist.CannotCompileException;
import javassist.ClassPool;

//@RunWith(PowerMockRunner.class)
//@PowerMockIgnore({"javax.crypto.*","javax.security.auth.*","javax.management.*"})
//@PrepareForTest({ProceedingJoinPoint.class,JoinPoint.class,Caching.class,CacheManager.class,Method.class,MethodSignature.class,StaticPart.class})
public class CachingTest {

	@MockStrict
	private ProceedingJoinPoint jointpoint;

	@MockStrict
	private MethodSignature signature;

	@MockStrict
	private StaticPart staticPart;



	//@MockStrict
	//	private JoinPoint jointpoint;

	@MockStrict
	private CacheManager cachemanager;

	//@Before
	public void beforeTest() {
		PowerMock.resetAll();  
		PowerMock.mockStaticStrict(ProceedingJoinPoint.class);
		PowerMock.mockStaticStrict(MethodSignature.class);
		PowerMock.mockStaticStrict(StaticPart.class);
		PowerMock.mockStaticStrict(CacheManager.class);
		PowerMock.mockStaticStrict(JoinPoint.class);


	}

	/**
	 * 
	 * When cacheable is call 
	 */
	//@Cache(engineName = "rediscache")
	//@Test
	public void cacheable_GET_OK() throws Throwable {
		String methodeName ="cacheable_GET_OK";
		String cacheName ="redis";
		Object[] params = {"polmlop", "string1", "string3"};
		Class[] clazzParam = new Class[3];
		clazzParam[0] = int.class;
		clazzParam[1] = String.class;
		clazzParam[2] = String.class;
		String value ="value";
	/*
		EasyMock.expect(jointpoint.getStaticPart()).andReturn(staticPart);
		EasyMock.expect(staticPart.getSignature()).andReturn(signature);
		EasyMock.expect(signature.getReturnType()).andReturn(List.class);
		EasyMock.expect(signature.getParameterTypes()).andReturn(clazzParam);
		EasyMock.expect(signature.getName()).andReturn("methode");
		Method mockMethod = this.getClass().getMethod(methodeName);
		mockMethod.setAccessible(true);
		
		
		//
		
		//
		EasyMock.expect(signature.getMethod()).andReturn(mockMethod);
		EasyMock.expect(jointpoint.getArgs()).andReturn(params);
		final Cache mockAnot = PowerMock.createMock(Cache.class);
		EasyMock.expect(mockMethod.getAnnotation(Cache.class)).andReturn(mockAnot);
		EasyMock.expect(CacheManager.getInstance()).andReturn(cachemanager);
		EasyMock.expect(cachemanager.get(EasyMock.anyObject())).andReturn(value);
		PowerMock.replayAll();
		Caching caching = new Caching();
		String expectvalue = (String) caching.cacheable(jointpoint);
		PowerMock.verify();
		Assert.assertEquals(expectvalue, value);*/

	}
	
	
	
	private void setAnnotationDynamically(Class clazz) throws CannotCompileException {
		 ClassPool cp = ClassPool.getDefault();
		 cp.makePackage(cp.getClassLoader(), clazz.getPackage().getName()); 
		 
		 
		 
	}
	/**
	 * 
	 * When cacheable is call 
	 */
	//@Test
	//@Cache(engineName = "rediscache")
	public void cacheable_update_OK() throws Throwable {
		String methodeName ="cacheable_update_OK";
		Caching caching = new Caching();
		String cacheName ="redis";
		Object[] params = {"polmlop", "string1", "string3"};
		Class[] clazzParam = new Class[3];
		clazzParam[0] = int.class;
		clazzParam[1] = String.class;
		clazzParam[2] = String.class;
		String value ="value";
		/*		EasyMock.expect(jointpoint.getStaticPart()).andReturn(staticPart);
		EasyMock.expect(staticPart.getSignature()).andReturn(signature);
		EasyMock.expect(signature.getReturnType()).andReturn(List.class);
		EasyMock.expect(signature.getParameterTypes()).andReturn(clazzParam);
		EasyMock.expect(signature.getName()).andReturn("methode");
		Method mockMethod = this.getClass().getMethod(methodeName);
		EasyMock.expect(signature.getMethod()).andReturn(mockMethod);
		EasyMock.expect(jointpoint.getArgs()).andReturn(params);
		EasyMock.expect(CacheManager.getInstance()).andReturn(cachemanager);
		cachemanager.put(EasyMock.anyObject(),EasyMock.eq(value));
		EasyMock.expectLastCall();
		PowerMock.replayAll();
		caching.updateCache(jointpoint, value);
		PowerMock.verify();*/

	}
	
	private void setAnnotation() {
		
	}
}
