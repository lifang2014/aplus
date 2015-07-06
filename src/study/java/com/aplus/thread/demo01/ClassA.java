package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/15.
 */
public class ClassA {

    private int i = 0;

    public synchronized void A(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        while (true){
        }
    }

    public synchronized void B(){
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        while (true){}
    }

}
