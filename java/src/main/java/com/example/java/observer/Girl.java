package com.example.java.observer;

public class Girl implements Observer{
    /**
     * 名字
     */
    private String name;
    public Girl(String name) {
        this.name = name;
    }

    /**
     * 女孩的具体反映
     * @param message
     */
    @Override
    public void update(String message) {
        System.out.println(name+"收到了信息："+message + "让男朋友去拿快递");
    }
}
