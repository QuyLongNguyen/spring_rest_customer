package com.longnguyenquy.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.longnguyenquy.restcontroller.*.*(..))")
	private void forControllerPackage() {};
	
	@Pointcut("execution(* com.longnguyenquy.service.*.*(..))")
	private void forServicePackage() {};
	
	@Pointcut("execution(* com.longnguyenquy.dao.*.*(..))")
	private void forDAOPackage() {};
	
	@Pointcut("forControllerPackage() ||forServicePackage() ||forDAOPackage()")
	private void forAppFlow() {};
	
	@Before("forAppFlow()")
	private void beforeCalling(JoinPoint jp) {
		
		String method = jp.getSignature().toShortString();
		myLogger.info("Before calling method"+method);
		
		Object[] args = jp.getArgs();
		
		for(Object temp: args) {
			myLogger.info("===> Argument: "+temp);
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()",  returning = "result")
	public void afterCalling(JoinPoint jp, Object result) {
		String method = jp.getSignature().toShortString();
		myLogger.info("===> In @AfterReturning: from method: "+ method);
		
		myLogger.info("===> Result: " + result);
	}
	
}
