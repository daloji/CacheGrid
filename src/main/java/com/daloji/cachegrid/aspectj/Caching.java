package com.daloji.cachegrid.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.daloji.cachegrid.BasicCache;

@Aspect
public class Caching {

	private BasicCache<Object> basicCache = new BasicCache<Object>();

	@Around("@annotation(com.daloji.cachegrid.aspectj.Cache)")
	public void cacheable(ProceedingJoinPoint thisJoinPoint_p) throws Throwable
	{
		
	}
    
    @Around("execution(@com.daloji.cache.aspectj.Cache * *(..))")
    public void logContinueProcess(ProceedingJoinPoint thisJoinPoint_p) throws Throwable
    {

    }
 
}
