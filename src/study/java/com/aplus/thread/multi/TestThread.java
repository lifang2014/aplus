package com.aplus.thread.multi;

/**
 * Created by lifang on 2015/6/29.
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        final int count = 2;
//        final ProductFactory productFactory = new ProductFactory();
//        for(int i = 0; i < count; i++){
//            new Thread(new Producer(productFactory)).start();
//            new Thread(new Consumer(productFactory)).start();
//        }

        class Task implements Runnable{
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("==========================================");
                }
            }
        };


        class Task2 implements Runnable{
            int count = 0;
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    count++;
                    if(count > 100){
                        break;
                    }
                }
            }
        };

        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task2());

        t2.start();
        t2.join();
        t1.start();
        for (int i = 0; i <100 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("************************************************************************");
        }
    }

}
