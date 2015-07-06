package com.aplus.thread.demo01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lifang on 2015/6/12.
 */
public class TicketDemo02 implements Runnable{
    private Logger logger = LoggerFactory.getLogger(TicketDemo02.class);

    private int max_value = 0;

    @Override
    public void run() {
        while (true){
            synchronized (this) {
                if (max_value > 500) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (Exception e) {

                }
                logger.info("{}, max_value : {}", Thread.currentThread().getName(), max_value++);
            }
        }
    }
}
