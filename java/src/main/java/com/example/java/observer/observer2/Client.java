package com.example.java.observer.observer2;

class Client {
   public static void main(String[] args) {
      Postman postman = new Postman();

      Observer boy = new Boy("路飞");
      Observer boy2 = new Boy("乔巴");
      Observer girl = new Girl("娜美");

      postman.add(boy);
      postman.add(boy2);
      postman.add(girl);

      postman.notify("快递到了，请下楼领取");
   }
}
