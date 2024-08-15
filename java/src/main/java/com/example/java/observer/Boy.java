package com.example.java.observer;

public class Boy implements Observer{
    /**
     * 名字
     */
    private String name;

    public Boy(String name) {
        this.name = name;
    }

    /**
     * 男孩的具体反映
     * @param message
     */
    @Override
    public void update(String message) {
        System.out.println(name + " 收到信息：" + message + "取快递");
    }
}

