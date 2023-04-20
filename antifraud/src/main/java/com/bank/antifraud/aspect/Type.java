package com.bank.antifraud.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, созданная для Entity сущностей, которая определяет тип подозрительного перевода
 *
 * @author Makariy Petrov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Type {
    String value();
}
