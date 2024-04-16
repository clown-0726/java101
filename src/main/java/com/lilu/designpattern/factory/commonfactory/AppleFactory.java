package com.lilu.designpattern.factory.commonfactory;

public class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }
}
