package com.lilu.oop.whyinterface;

public class Calc {
    public static Computer getComputer(int cond) throws Exception {
        if (cond == 1) {
            return new Desktop();
        } else if (cond == 2) {
            return new Laptop();
        } else {
            throw new Exception("Wrong cond...");
        }
    }

    public static void main(String[] args) throws Exception {
        int cond = 1;
        Computer c = getComputer(1);
        String str = c.getContent("http://abc.com/");
        System.out.println(str);
    }
}
