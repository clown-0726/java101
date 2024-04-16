package com.lilu.designpattern.factory.simplefactory;

public class Client {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Phone miPhone = factory.makePhone("MiPhone");            // make xiaomi phone!
        IPhone iPhone = (IPhone) factory.makePhone("iPhone");    // make iphone!
    }
}
