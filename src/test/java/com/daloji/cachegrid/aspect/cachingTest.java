package com.daloji.cachegrid.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.MockStrict;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.daloji.cachegrid.aspectj.Caching;

//@RunWith(PowerMockRunner.class)
//@PowerMockIgnore({"javax.crypto.*","javax.security.auth.*"})
//@PrepareForTest({ProceedingJoinPoint.class,MethodSignature.class,StaticPart.class})
public class cachingTest {
	
	//@MockStrict
	private ProceedingJoinPoint joinpoint;
	
	//@MockStrict
	private MethodSignature signature;
	
	//@MockStrict
	private StaticPart staticPart;
	
	//@Before
	public void beforeTest() {
		PowerMock.resetAll();
		PowerMock.mockStaticStrict(ProceedingJoinPoint.class);
		PowerMock.mockStaticStrict(MethodSignature.class);
		PowerMock.mockStaticStrict(StaticPart.class);


	}
	
	//@Test
	public void cacheable() throws Throwable {
		Caching caching = new Caching();
		Object[] params = {"polmlop", "string1", "string3"};
		Class[] clazzParam = new Class[3];
		clazzParam[0] = int.class;
		clazzParam[1] = String.class;
		clazzParam[2] = String.class;
		

		EasyMock.expect(joinpoint.getStaticPart()).andReturn(staticPart);
		EasyMock.expect(staticPart.getSignature()).andReturn(signature);
		EasyMock.expect(signature.getReturnType()).andReturn(List.class);
		EasyMock.expect(signature.getParameterTypes()).andReturn(clazzParam);
		EasyMock.expect(joinpoint.getArgs()).andReturn(params);

		PowerMock.replayAll();

		
		caching.cacheable(joinpoint);
		PowerMock.verify();
	}
}
