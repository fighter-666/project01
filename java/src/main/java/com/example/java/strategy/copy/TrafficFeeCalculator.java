package com.example.java.strategy.copy;

/**
 * 使用算法
 */
class TrafficFeeCalculator {
   public int goToTianJinEye(CalculateStrategy strategy, int distance){
      return strategy.calculate(distance);
   }
}
