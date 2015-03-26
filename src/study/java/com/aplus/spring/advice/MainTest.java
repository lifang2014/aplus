package com.aplus.spring.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by lifang on 2015/2/7.
 */
public class MainTest {

    public static void main(String[] args){

        Waiter waiter = new NaiveWaiter();
        BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();

        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setTarget(waiter);

        proxyFactory.addAdvice(beforeAdvice);


        Waiter waiter1 = (Waiter)proxyFactory.getProxy();

        waiter1.greetTo("Li.MING");
        waiter1.serveTo("SEE YOU");
    }

}
