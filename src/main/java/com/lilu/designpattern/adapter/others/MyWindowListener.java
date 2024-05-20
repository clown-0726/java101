package com.lilu.designpattern.adapter.others;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyWindowListener {
    public static void main(String[] args) {
        Frame frame = new Frame();

        // WindowAdapter 实现了 WindowListener 接口的抽象类
        // 内部是方法的空实现，方便使用进行自定义覆盖，而不用强制重写所有接口要求的方法
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }
        });

        // addWindowListener 要求的参数是一个继承了 WindowListener 接口的类
        // 但是如果直接使用一个这样的类，则需要实现接口中所有要求的方法，正常情况下，常用的方法只有几个
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }
}
