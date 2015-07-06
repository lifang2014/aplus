package com.aplus.thread.multi;

/**
 * Created by lifang on 2015/6/29.
 */
public class Producer extends AbsFactory{

    public Producer(ProductFactory productFactory) {
        super(productFactory);
    }

    @Override
    public void execute() {
        productFactory.create();
    }
}
