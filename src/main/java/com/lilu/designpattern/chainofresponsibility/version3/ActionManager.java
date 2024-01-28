package com.lilu.designpattern.chainofresponsibility.version3;

public class ActionManager extends Action {
    public ActionManager(String name, Action nextAction) {
        super(name, nextAction);
    }

    @Override
    public void handle() {
        System.out.println("Handler by..." + this.name);
        if (this.nextAction != null) {
            this.nextAction.handle();
        }
    }
}
