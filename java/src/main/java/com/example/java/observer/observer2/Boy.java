package com.example.java.observer.observer2;

public class Boy implements Observer {

    private String name;

    public Boy(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "，收到消息：" + message + "屁颠颠的去取快递。");
    }
}
