package com.example.java.strategy.copy;

class ByDiDiExpress implements CalculateStrategy{
   @Override
   public int calculate(int distance) {
      return distance<3?8:(8+(distance-3)*3);
   }
}
