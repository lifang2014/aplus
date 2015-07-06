package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/15.
 */
public class TestPrintABC {

    public static void main(String[] args) throws Exception{

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        PrintABC pa = new PrintABC("A", a, c);
        PrintABC pb = new PrintABC("B", b, a);
        PrintABC pc = new PrintABC("C", c, b);

        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
    }
}
