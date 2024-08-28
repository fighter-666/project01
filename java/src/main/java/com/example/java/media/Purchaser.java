package com.example.java.media;

class Purchaser extends Person{
   public Purchaser(HouseMediator houseMediator) {
      super(houseMediator);
   }

   @Override
   public void send(String message) {
      System.out.println("买房者发布信息：" + message);
      houseMediator.notice(this, message);
   }

   @Override
   public void getNotice(String message) {
      System.out.println("买房者收到信息："+ message);

   }
}
