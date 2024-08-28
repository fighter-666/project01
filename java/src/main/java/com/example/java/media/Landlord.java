package com.example.java.media;

public class Landlord extends Person{
   public Landlord(HouseMediator houseMediator) {
      super(houseMediator);
   }

   @Override
   public void send(String message) {
      System.out.println("房东发布信息："+ message);
      houseMediator.notice(this,message);
   }

   @Override
   public void getNotice(String message) {
      System.out.println("房东收到信息："+ message);
   }
}
