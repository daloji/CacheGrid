package com.daloji.cachegrid.aspectj;


import static java.util.Objects.isNull;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.daloji.cachegrid.CacheManager;
import com.daloji.cachegrid.common.AspectParam;

/**
 * AsepctJ Entry point  contains all pointcut 
 * @author daloji
 *
 */
@Aspect
public class Caching {

	private CacheManager cacheManager = null;

	@Around("execution(* *(..)) && @annotation(com.daloji.cachegrid.aspectj.Cache)")
	public <T> Object cacheable(ProceedingJoinPoint joinPoint) throws Throwable
	{
		cacheManager = CacheManager.getInstance();
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
		cacheManager = CacheManager.getInstance();
		AspectParam<T> param = new AspectParam<>();
		param.setParamJointPoint(joinPoint);
		cacheManager.put(param,result);
	}

}