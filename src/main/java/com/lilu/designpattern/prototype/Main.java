package com.lilu.designpattern.prototype;

public class Main {
    public static void main(String[] args) {
        // 先预先创建需要的形状并存储在 HashTable 中
        ShapeCache.loadCache();

        // 调用 getShape 方法，clone 一个新的
        Shape clonedShape = (Shape) ShapeCache.getShape("circle");
        clonedShape.draw();

        // 调用 getShape 方法，clone 一个新的
        Shape clonedShape3 = (Shape) ShapeCache.getShape("rectangle");
        clonedShape3.draw();
    }
}
