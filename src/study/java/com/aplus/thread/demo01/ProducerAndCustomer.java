package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/15.
 */
public class ProducerAndCustomer {

    private final static NumberFactory numberFactory = new NumberFactory();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    numberFactory.create();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    numberFactory.consume();
                }
            }
        }).start();

    }
}
