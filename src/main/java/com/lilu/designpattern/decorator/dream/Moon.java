package com.lilu.designpattern.decorator.dream;

public class Moon implements AnyThing {
    protected AnyThing anyThing;

    public Moon(AnyThing anyThing) {
        this.anyThing = anyThing;
    }

    @Override
    public void exec() {
        System.out.println(" 明月装饰了 ");
        anyThing.exec();
    }
}
