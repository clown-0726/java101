package com.lilu.designpattern.chainofresponsibility.version3;

public class Main {
    public static void main(String[] args) {
        Action actionManager = new ActionManager("Manager", null);
        Action actionLeader = new ActionLeader("Leader", actionManager);
        Action actionMember = new ActionMember("Member", actionLeader);
        actionMember.handle();
    }
}
