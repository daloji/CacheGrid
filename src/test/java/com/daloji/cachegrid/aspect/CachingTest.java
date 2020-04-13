package com.daloji.cachegrid.aspect;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.MockStrict;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.daloji.cachegrid.aspectj.Cache;
import com.daloji.cachegrid.aspectj.Caching;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.crypto.*","javax.security.auth.*"})
@PrepareForTest({ProceedingJoinPoint.class,MethodSignature.class,StaticPart.class})
public class CachingTest {

	@MockStrict
	private ProceedingJoinPoint joinpoint;

	@MockStrict
	private MethodSignature signature;

	@MockStrict
	private StaticPart staticPart;
	

//	@MockStrict
	//private Method method;
	
	@MockStrict
	private Cache cache;

	@Before
	public void beforeTest() {
		PowerMock.resetAll();
		PowerMock.mockStaticStrict(ProceedingJoinPoint.class);
		PowerMock.mockStaticStrict(MethodSignature.class);
		PowerMock.mockStaticStrict(StaticPart.class);
		//PowerMock.mockStaticStrict(Method.class);
		PowerMock.mockStaticStrict(Cache.class);


	}

	//@Test
	public void cacheable() throws Throwable {
		Caching caching = new Caching();
		String cacheName ="redis";
		Object[] params = {"polmlop", "string1", "string3"};
		Class[] clazzParam = new Class[3];
		clazzParam[0] = int.class;
		clazzParam[1] = String.class;
		clazzParam[2] = String.class;


		EasyMock.expect(joinpoint.getStaticPart()).andReturn(staticPart);
		EasyMock.expect(staticPart.getSignature()).andReturn(signature);
		EasyMock.expect(signature.getReturnType()).andReturn(List.class);
		EasyMock.expect(signature.getParameterTypes()).andReturn(clazzParam);
		
		Method method = EasyMock.createMock(Method.class);
		EasyMock.expect(signature.getMethod()).andReturn(method);
		//EasyMock.expect(joinpoint.getArgs()).andReturn(params);
		//EasyMock.expect(joinpoint.getArgs().).andReturn(params);

		//EasyMock.expect(signature.getMethod()
				
				//.getAnnotation(Cache.class)).andReturn(cache);
		
		//andReturn(method);
	//EasyMock.expect(method.getAnnotation(Cache.class)).andReturn(cache);
		//EasyMock.expect(cache.engineName()).andReturn(cacheName);

		
		PowerMock.replayAll();
		caching.cacheable(joinpoint);
		PowerMock.verify();
	}
}
