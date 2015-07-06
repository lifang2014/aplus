package com.aplus.thread.demo02;

/**
 * Created by lifang on 2015/7/6.
 */
public class RunPrintABC implements Runnable{

    private String text;
    private Object runObj = new Object();
    private Object waitObj = new Object();

    public RunPrintABC(String text, Object runObj, Object waitObj) {
        this.text = text;
        this.runObj = runObj;
        this.waitObj = waitObj;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            synchronized (waitObj){
                synchronized (runObj){
                    System.out.println(text);
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
