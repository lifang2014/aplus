package com.aplus.gof.command;

/**
 * Created by lifang on 2015/6/25.
 */
public class Customer {

    private Command command;

    public Customer(Command command){
        this.command = command;
    }

    /**
     * 执行
     */
    public void order(){
        command.execute();
    }

    /**
     * 撤销
     */
    public void undo(){
        command.undo();
    }
}
