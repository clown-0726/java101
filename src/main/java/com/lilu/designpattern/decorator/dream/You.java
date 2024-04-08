package com.lilu.designpattern.decorator.dream;

public class You implements AnyThing {
    protected AnyThing anyThing;

    public You(AnyThing anyThing) {
        this.anyThing = anyThing;
    }

    @Override
    public void exec() {
        System.out.println(" 你 ");
    }
}
