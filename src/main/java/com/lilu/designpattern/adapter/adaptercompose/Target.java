package com.lilu.designpattern.adapter.adaptercompose;

public class Target implements TargetInterface {
    @Override
    public void require10() {
        int result = new Adapter().provider10();
        System.out.println(result);
    }

    /**
     * 测试，需要的是实现了 TargetInterface 的类，通过 require10 方法返回 10
     *
     * @param args
     */
    public static void main(String[] args) {
        new Target().require10();
    }
}
