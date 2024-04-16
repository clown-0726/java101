package com.lilu.designpattern.factory.commonfactory;

public class XiaoMiFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}
