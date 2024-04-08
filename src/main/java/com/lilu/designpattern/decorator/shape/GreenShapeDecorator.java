package com.lilu.designpattern.decorator.shape;

public class GreenShapeDecorator extends ShapeDecorator {
    public GreenShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setGreenBorder();
    }

    private void setGreenBorder() {
        System.out.println("Border Color: Green");
    }
}
