package com.aplus.thread.multi;

/**
 * Created by lifang on 2015/6/29.
 */
public class ProductFactory {

    private int i = 0;

    public boolean isCreated = false;

    public synchronized void create(){
//        synchronized (this){
//            if(isCreated){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                i += 1;
//                System.out.println(Thread.currentThread().getName() + " -- create : i = " + i);
//                notify();
//                isCreated = true;
//            }
//        }


        while (isCreated){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        i += 1;
        System.out.println(Thread.currentThread().getName() + " -- create : i = " + i);
        notifyAll();
        isCreated = true;

    }
    public synchronized void consume(){
//        synchronized (this){
//            if(isCreated){
//                System.out.println(Thread.currentThread().getName() + " -- consume : i = " + i);
//                notify();
//                isCreated = false;
//            }else {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        while (isCreated){
            System.out.println(Thread.currentThread().getName() + " -- consume : i = " + i);
            notifyAll();
            isCreated = false;
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
