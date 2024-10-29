package org.file.grain.enrichment.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.file.grain.enrichment.model.Person;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

	@Pointcut("execution(* org.file.grain.enrichment.api.*.*(..))")
	//@Pointcut("within(org.file.grain.enrichment..*)")
	//@Pointcut("this(org.file.grain.enrichment.service.PersonService)")
	//@Pointcut("@annotation(org.file.grain.enrichment.annotation.CustomAnnotation)")
	public void loggingPointCut() {

	}

//	@Before("loggingPointCut()")
//	public void before(JoinPoint joinPoint) {
//		log.info("Before method invoked : " + joinPoint.getSignature());
//
//	}

//	@After("loggingPointCut()")
//	public void after(JoinPoint joinPoint) {
//		log.info("After method invoked : " + joinPoint.getSignature());
//
//	}

//	@AfterReturning(value = "execution(* org.file.grain.enrichment.api.*.*(..))", returning = "person")
//	public void after(JoinPoint joinPoint, Person person) {
//		log.info("After method invoked : " + person);
//
//	}
//	
//	
//	@AfterThrowing(value = "execution(* org.file.grain.enrichment.api.*.*(..))", throwing  = "e")
//	public void after(JoinPoint joinPoint, Exception e) {
//		log.info("After method invoked : " + e.getMessage());
//
//	}

	@Around("loggingPointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Before method invoked : " + joinPoint.getSignature());
		Object object = joinPoint.proceed();
		log.info("After method invoked ..." + joinPoint.getArgs()[0]);
		return object;

	}

}
