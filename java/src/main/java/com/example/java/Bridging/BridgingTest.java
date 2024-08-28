package com.example.java.Bridging;

class BridgingTest {
   public static void main(String[] args) {
      RefinedCoffee largeWithMilk = new LargeCoffee(new Milk());
      largeWithMilk.orderCoffee(2);
      largeWithMilk.checkQuality();
   }
}
