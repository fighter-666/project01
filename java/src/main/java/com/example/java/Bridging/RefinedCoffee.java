package com.example.java.Bridging;

import java.util.Random;

public abstract class RefinedCoffee extends Coffee {
   public RefinedCoffee(ICoffeeAdditives additives) {
      super(additives);
   }

   public void checkQuality(){
      Random random = new Random();
      System.out.println(String.format("%s 添加%s",additives.getClass().getSimpleName(),
              random.nextBoolean()?"太多":"正常"));
   }
}
