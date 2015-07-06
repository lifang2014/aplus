package com.aplus.thread.demo02;

import com.aplus.thread.demo01.PrintABC;

/**
 * Created by lifang on 2015/7/6.
 */
public class TestPrintABC {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        new Thread(new PrintABC("A", a, c)).start();
        Thread.sleep(10);
        new Thread(new PrintABC("B", b, a)).start();
        Thread.sleep(10);
        new Thread(new PrintABC("C\n", c, b)).start();
    }

}
