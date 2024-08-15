package com.example.java.interfacedetail.test1;

public class Main implements InterfaceExample {
    public static void main(String[] args) {
        System.out.println("---接口使用测试---");
        InterfaceTest interfaceTest = new InterfaceTest();
        interfaceTest.handleThings(new Main(), "娃哈哈");
        System.out.println("异步回调测试");
    }

    @Override
    public void sendMessage(String string) {
        System.out.println("收到消息：" + string);

    }
}

interface InterfaceExample {
    void sendMessage(String string);
}
