package com.aplus.gof.command;

/**
 * Created by lifang on 2015/6/25.
 */
public interface Command {

    /**
     * 执行
     */
    public void execute();

    /**
     * 撤销
     */
    public void undo();
}
