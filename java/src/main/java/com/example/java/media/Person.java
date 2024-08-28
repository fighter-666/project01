package com.example.java.media;

public abstract class Person {
   protected HouseMediator houseMediator;

   public Person(HouseMediator houseMediator) {
      this.houseMediator = houseMediator;
   }

   public abstract void send(String message);

   public abstract void getNotice(String message);
}
