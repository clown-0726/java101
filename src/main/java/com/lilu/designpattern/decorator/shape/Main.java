package com.lilu.designpattern.decorator.shape;

public class Main {
    public static void main(String[] args) {
        //Circle circle = new Circle();
        Rectangle rectangle = new Rectangle();

        // 可以多层装饰
        GreenShapeDecorator greenShapeDecorator = new GreenShapeDecorator(rectangle);
        RedShapeDecorator redShapeDecorator = new RedShapeDecorator(greenShapeDecorator);
        redShapeDecorator.draw();
    }
}
