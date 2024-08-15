package com.example.java.interfacedetail.test1;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("---接口使用测试---");
        InterfaceTest interfaceTest = new InterfaceTest();
        interfaceTest.handleThings(new InterfaceExample() {
            @Override
            public void sendMessage(String string) {
                System.out.println("接口回调成功：" + string);
            }
        }, "娃哈哈6666");
        System.out.println("异步回调测试");
    }


}

