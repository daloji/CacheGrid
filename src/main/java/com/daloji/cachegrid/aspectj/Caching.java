package com.daloji.cachegrid.aspectj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.daloji.cachegrid.common.AspectParam;
import com.daloji.cachegrid.system.CacheManager;

@Aspect
public class Caching {

    CacheManager cacheManager = CacheManager.getInstance();
	
	@Around("execution(* *(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)")
	public <T> Object cacheable(ProceedingJoinPoint joinPoint) throws Throwable
	{
		AspectParam<T> param = new AspectParam<>();
		System.out.println("icici");
		param.setParamJointPoint(joinPoint);
		//thisJoinPoint.	
		return joinPoint.proceed();
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



	




}