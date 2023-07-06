package com.bank.antifraud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Аспект, который проводит логирование сервиса
 *
 * @author Makariy Petrov
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.bank.antifraud.controller..*(..)) || execution(* com.bank.antifraud.service..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.info("Метод " + joinPoint.getSignature().getName() +
                " в классе " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                " вызван с аргументами " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* com.bank.antifraud.controller..*(..)) || execution(* com.bank.antifraud.service..*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        LOGGER.error("Ошибка в методе " + joinPoint.getSignature().getName() +
                " в классе " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                " с аргументами " + Arrays.toString(joinPoint.getArgs()) + ": " +
                ex.getMessage());
    }
}