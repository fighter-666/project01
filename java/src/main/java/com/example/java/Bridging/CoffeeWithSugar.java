package com.example.java.Bridging;

class CoffeeWithSugar implements ICoffee{
   @Override
   public void orderCoffee(int count) {
      System.out.println("加糖咖啡" + count + "杯");
   }
}
