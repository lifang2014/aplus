package com.aplus.spring.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by lifang on 2015/2/7.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String clientName = (String)objects[0];
        System.out.println("HOW ARE YOU! MR." + clientName);
    }
}
