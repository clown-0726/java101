package com.lilu.misc.lambda;

public class LambdaForm {
    public static void main(String[] args) {
        // 形式一：没有参数
        SayHello sayHello1 = () -> System.out.println("Hello");
        sayHello1.say();

        // 形式二：带有参数
        SayHelloWithArg sayHello2 = name -> System.out.println(name);
        sayHello2.say("Hello");

        // 形式三：没有参数，逻辑复杂
        SayHello sayHello3 = () -> {
            System.out.println("Hello");
            System.out.println("World");
        };
        sayHello3.say();

        // 形式四：包含两个参数的方法
        BinaryOperator<Long> functionAdd1 = (x, y) -> x + y;
        Long result1 = functionAdd1.apply(1L, 2L);
        System.out.println(result1);

        // 形式五：对参数显示声明
        BinaryOperator<Long> functionAdd2 = (Long x, Long y) -> x + y;
        Long result2 = functionAdd2.apply(1L, 2L);
        System.out.println(result2);

    }

    interface SayHello {
        void say();
    }

    interface SayHelloWithArg {
        void say(String name);
    }

    interface BinaryOperator<T> {
        long apply(T x, T y);
    }
}
