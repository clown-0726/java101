package com.lilu.designpattern.factory.absctractfactory;

public class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }

    @Override
    public PC makePC() {
        return new MAC();
    }
}
