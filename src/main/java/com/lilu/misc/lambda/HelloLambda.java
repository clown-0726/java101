package com.lilu.misc.lambda;

public class HelloLambda {
    interface MathOperation {
        int operate(int a, int b);
    }

    public static void main(String[] args) {
        // 传统的方式，通过匿名类来实现接口
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        int result = addition.operate(3, 5);
        System.out.println(result);

        // 通过 Lambda 的方式实现接口
        MathOperation additionLambda = (x, y) -> x + y;
        int resultLambda = additionLambda.operate(3, 5);
        System.out.println(resultLambda);
    }
}
