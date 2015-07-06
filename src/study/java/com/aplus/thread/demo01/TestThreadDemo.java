package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/12.
 */
public class TestThreadDemo {

    public static void main(String[] args){
//        TicketDemo01 t1 = new TicketDemo01();
//        TicketDemo01 t2= new TicketDemo01();
//        TicketDemo01 t3= new TicketDemo01();
//        t1.start();
//        t2.start();
//        t3.start();


//        TicketDemo02 t = new TicketDemo02();
//        Thread t1 = new Thread(t);
//        Thread t2 = new Thread(t);
//        Thread t3 = new Thread(t);
//        t1.start();
//        t2.start();
//        t3.start();


        final ClassA classA = new ClassA();

        new Thread(new Runnable() {
            @Override
            public void run() {
                classA.A();
                classA.A();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                classA.B();
//            }
//        }).start();

    }

}
