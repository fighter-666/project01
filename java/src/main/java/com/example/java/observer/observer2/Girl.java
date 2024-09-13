package com.example.java.observer.observer2;

class Girl implements Observer {
    private String name;

    public Girl(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "，收到消息：" + message + "让男朋友去取快递。");
    }
}
