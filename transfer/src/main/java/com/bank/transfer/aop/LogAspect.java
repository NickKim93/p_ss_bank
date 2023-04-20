package com.bank.transfer.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("Pointcuts.getAllMethods()")
    public Object aroundGetAllAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        log.info("[AOP LOG from method {}] from Service try to get All Transfers", methodName);
        Object result = joinPoint.proceed();
        var list = (ArrayList<?>) result;
        log.info("[AOP LOG from method {}] from Service getAll transfers success, count = {}", methodName, list.size());
        return result;
    }


    @Around("@annotation(DeleteToLog)")
    public void aroundDeleteAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Long id = (Long) arguments[0];
        log.info("[AOP LOG from method {}] from Service try to delete transfer with id={}", methodName, id);
        joinPoint.proceed();
        log.info("[AOP LOG from method {}] from Service delete transfer with id={} success", methodName, id);
    }

}
