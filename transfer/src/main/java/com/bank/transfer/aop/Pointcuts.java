package com.bank.transfer.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.bank.transfer.service.*.getAll(..))")
    public void getAllMethods(){

    }
}
