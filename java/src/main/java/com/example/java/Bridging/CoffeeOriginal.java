package com.example.java.Bridging;

class CoffeeOriginal implements ICoffee{
   @Override
   public void orderCoffee(int count) {
      System.out.println("原味咖啡" + count + "杯");
   }
}
