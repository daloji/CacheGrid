package com.daloji.cachegrid.aspectj;


import static java.util.Objects.isNull;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.daloji.cachegrid.common.AspectParam;
import com.daloji.cachegrid.system.CacheManager;

/**
 * AsepctJ Entry point  contains all pointcut 
 * @author daloji
 *
 */
@Aspect
public class Caching {

	CacheManager cacheManager = CacheManager.getInstance();

	@Around("execution(* *(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)")
	public <T> Object cacheable(ProceedingJoinPoint joinPoint) throws Throwable
	{
		AspectParam<T> param = new AspectParam<>();
		param.setParamJointPoint(joinPoint);
		Object objt = cacheManager.get(param);
		if(isNull(objt)) {
			objt = joinPoint.proceed();
		}

		return objt;
	}


	@AfterReturning(
			pointcut = "execution(* *(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)",
			returning= "result")
	public <T> void updateCache(JoinPoint joinPoint, Object result) throws Throwable
	{
		AspectParam<T> param = new AspectParam<>();
		param.setParamJointPoint(joinPoint);
		cacheManager.put(param,result);
		System.out.println("After");
	}

}