package com.daloji.cachegrid.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.daloji.cachegrid.BasicCache;

@Aspect
public class Caching {

	private BasicCache<Object> basicCache = new BasicCache<Object>();

	//@Around("execution(@com.daloji.cache.aspectj.Cache * *(..))")
    //@Around("execution(* *(..)) && @annotation(com.daloji.cache.aspectj.Cache)")
	 // @Around("execution(@com.bytel.ravel.services.aspectJ.LogStartProcess * *(..))")
	@Around("@annotation(com.daloji.cache.aspectj.Cache)")
	public void cacheable(ProceedingJoinPoint thisJoinPoint_p) throws Throwable
	{
		
		System.out.println("ICICICIICIICICICICICIICIC");
		
	}
    
    
    //@Around("execution(@com.daloji.cache.aspectj.Cache * *(..))")
    public void logContinueProcess(ProceedingJoinPoint thisJoinPoint_p) throws Throwable
    {
    	System.out.println("ICICICIICIICICICICICIICIC");
    }
    /*
    @Pointcut("execution(* *(..)) && @annotation(com.daloji.cache.aspectj.Cache)")
    public void callAt() {}

    @Around(("callAt()"))
    public void doSomething(ProceedingJoinPoint point)  {
      //point.proceed();
      System.out.println("Aspect is runnning...");
    
    
    }*/
}
