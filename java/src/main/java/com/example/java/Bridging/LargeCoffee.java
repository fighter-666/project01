package com.example.java.Bridging;

public class LargeCoffee extends RefinedCoffee{
   public LargeCoffee(ICoffeeAdditives additives) {
      super(additives);
   }

   @Override
   public void orderCoffee(int count) {
      additives.addSomething();
      System.out.println(String.format("大杯咖啡%d",count));
   }
}
