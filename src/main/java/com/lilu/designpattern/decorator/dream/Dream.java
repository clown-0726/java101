package com.lilu.designpattern.decorator.dream;

public class Dream implements AnyThing {
    protected AnyThing anyThing;

    public Dream(AnyThing anyThing) {
        this.anyThing = anyThing;
    }

    @Override
    public void exec() {
        if (this.anyThing != null) {
            System.out.println(" 梦装饰了 ");
            anyThing.exec();
        } else {
            System.out.println(" 梦 ");
        }
    }
}
