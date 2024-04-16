package com.lilu.designpattern.factory.absctractfactory;

public class Client {
    public AbstractFactory factory;
    public Phone phone;
    public PC pc;

    public Client(AbstractFactory abstractFactory) {
        this.factory = abstractFactory;
        this.phone = this.factory.makePhone();
        this.pc = this.factory.makePC();
        this.phone.make();
        this.pc.make();
    }

    public static void main(String[] args) {
        AbstractFactory miFactory = new XiaoMiFactory();
        Client client1 = new Client(miFactory);

        AbstractFactory appleFactory = new AppleFactory();
        Client client2 = new Client(appleFactory);
    }
}
