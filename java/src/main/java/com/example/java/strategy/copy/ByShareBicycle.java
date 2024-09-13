package com.example.java.strategy.copy;

class ByShareBicycle implements CalculateStrategy{
   @Override
   public int calculate(int distance) {
      return 2;
   }
}
