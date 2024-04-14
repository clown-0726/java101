package com.lilu.designpattern.decorator.dream;

public class Life implements AnyThing {
    protected AnyThing anyThing;

    public Life(AnyThing anyThing) {
        this.anyThing = anyThing;
    }

    @Override
    public void exec() {
        if (this.anyThing != null) {
            System.out.println(" 生活装饰了 ");
            this.anyThing.exec();
        } else {
            System.out.println(" 生活 ");
        }
    }
}
