package com.aplus.thread.demo01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lifang on 2015/6/12.
 */
public class TicketDemo01 extends Thread{

    int max_value = 0;
    private Logger logger = LoggerFactory.getLogger(TicketDemo01.class);

    @Override
    public void run() {
        while (true){
            if(max_value > 50){
                break;
            }
            logger.info("{},max_value : {}", currentThread().getName(), max_value++);
        }
    }

}
