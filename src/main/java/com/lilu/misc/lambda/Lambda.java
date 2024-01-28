package com.lilu.misc.lambda;

public class Lambda {
    public static void main(String[] args) {
        // 形式一：没有参数
        //SayHello sayHello = () -> System.out.println("Hello");
        //sayHello.say();

        // 形式一：没有参数
        //SayHello sayHello = name -> System.out.println(name);
        //sayHello.say("Hello");

        // 形式三：没有参数，逻辑复杂
        //SayHello sayHello = () -> {
        //    System.out.println("Hello");
        //    System.out.println("World");
        //};
        //sayHello.say();

        // 形式四：包含两个参数的方法
        //BinaryOperator<Long> functionAdd = (x, y) -> x + y;
        //Long result = functionAdd.apply(1L, 2L);
        //System.out.println(result);

        // 形式五：对参数显示声明
        //BinaryOperator<Long> functionAdd = (Long x, Long y) -> x + y;
        //Long result = functionAdd.apply(1L, 2L);
        //System.out.println(result);

    }

    interface MathOperation {
        int operate(int a, int b);
    }


    interface SayHello {
        void say();
    }
}
