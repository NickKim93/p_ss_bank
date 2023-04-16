package com.bank.antifraud.aspect;

import com.bank.antifraud.util.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, которая указывает на то, что действие данного метода нужно записать в таблицу Audit
 *
 * @author Makariy Petrov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auditing {

    OperationType operationType();
}
