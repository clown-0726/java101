package com.lilu.annotations.resolve;


@ResolveAnnotation("I am Param.")
public class Resolve {
    public static void main(String[] args) {
        Resolve resolve = new Resolve();
        Class c = resolve.getClass();
        ResolveAnnotation annotation = (ResolveAnnotation) c.getAnnotation(ResolveAnnotation.class);
        System.out.println(annotation.value());
    }
}
