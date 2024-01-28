package com.lilu.designpattern.chainofresponsibility.version3;

public class ActionLeader extends Action {
    public ActionLeader(String name, Action nextAction) {
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
