package com.aplus.gof.command;

/**
 * Created by lifang on 2015/6/25.
 */
public class TestCommand {

    public static void main(String[] args) {
        CookReceiver cookReceiver = new CookReceiver();
        Command command = new OrderCommand(cookReceiver);
        command.execute();
        command.undo();
    }
}
