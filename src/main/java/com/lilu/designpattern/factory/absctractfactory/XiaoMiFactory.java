package com.lilu.designpattern.factory.absctractfactory;

public class XiaoMiFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }

    @Override
    public PC makePC() {
        return new MiPC();
    }
}
