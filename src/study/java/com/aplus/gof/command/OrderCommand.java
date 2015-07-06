package com.aplus.gof.command;

/**
 * Created by lifang on 2015/6/25.
 */
public class OrderCommand implements Command{

    private CookReceiver receiver;

    public OrderCommand(CookReceiver cookReceiver){
        this.receiver = cookReceiver;
    }

    /**
     * 执行
     */
    @Override
    public void execute() {
        receiver.cooking();
    }

    /**
     * 撤销
     */
    @Override
    public void undo() {
        receiver.unCooking();
    }
}
