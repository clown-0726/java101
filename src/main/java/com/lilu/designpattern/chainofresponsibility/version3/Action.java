package com.lilu.designpattern.chainofresponsibility.version3;

public abstract class Action {
    public String name;
    public Action nextAction = null;

    public Action(String name, Action nextAction) {
        this.name = name;
        this.nextAction = nextAction;
    }

    public abstract void handle();
}
