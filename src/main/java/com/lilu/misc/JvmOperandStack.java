package com.lilu.misc;

public class JvmOperandStack {
    public static void main(String[] args) {
        JvmOperandStack jvmOperandStack = new JvmOperandStack();
        int d = jvmOperandStack.add(1, 2);
        System.out.println(d);
    }

    public int add(int a, int b) {
        int c = a + b;
        return a + b + c;
    }
}
