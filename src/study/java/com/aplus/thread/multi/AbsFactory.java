package com.aplus.thread.multi;

/**
 * Created by lifang on 2015/6/29.
 */
public abstract class AbsFactory implements Runnable {

    protected ProductFactory productFactory;

    public AbsFactory(ProductFactory productFactory){
        this.productFactory = productFactory;
    }

    abstract public void execute();

    public void run(){
        while (true){
            execute();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
