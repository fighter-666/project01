package com.example.java.strategy.copy;

class ByBus implements CalculateStrategy{
   @Override
   public int calculate(int distance) {
      return distance<10?4:6;
   }
}
