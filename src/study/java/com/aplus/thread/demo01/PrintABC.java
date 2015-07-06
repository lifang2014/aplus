package com.aplus.thread.demo01;

/**
 * Created by lifang on 2015/6/15.
 */
public class PrintABC implements Runnable{

    private String text;
    private Object runObj;
    private Object waitObj;


    public PrintABC(String text, Object runObj, Object waitObj) {
        this.text = text;
        this.runObj = runObj;
        this.waitObj = waitObj;
    }

    @Override
    public void run() {
        int count = 10;
        while(count > 0){
            synchronized (waitObj){
                synchronized (runObj){
                    System.out.print(text);
                    count --;
                    runObj.notify();
                }
                try {
                    waitObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
