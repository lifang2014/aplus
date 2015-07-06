package com.aplus.thread.multi;

/**
 * Created by lifang on 2015/6/29.
 */
public class Consumer extends AbsFactory{

    public Consumer(ProductFactory productFactory) {
        super(productFactory);
    }

    @Override
    public void execute() {
        productFactory.consume();
    }
}
