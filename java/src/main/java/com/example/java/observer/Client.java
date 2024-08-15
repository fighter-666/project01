package com.example.java.observer;

class Client {
    public static void main(String[] args) {
        Observable postman = new Postman();

        Observer boy1 = new Boy("张三");
        Observer boy2 = new Boy("22");
        Observer boy3 = new Girl("44");

        postman.add(boy1);
        postman.add(boy2);
        postman.add(boy3);

        postman.notify("有新快递到了");
    }
}
