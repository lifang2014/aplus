package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/15.
 */
public class NumberFactory {

    private int i = 0;

    private Object lock = new Object();

    private boolean created = false;

    public void create(){
        synchronized (lock) {
            if(!created) {
                i++;
                System.out.println("create i = " + i);
                lock.notify();
                created = true;
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume(){
        synchronized (lock) {
            if(created) {
                System.out.println("consume i = " + i);
                lock.notify();
                created = false;
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
