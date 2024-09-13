package com.example.java.strategy.copy;

/**
 * 根据情况，构建相应的算法实例，传入计算器即可
 */
class Test {
   public static void main(String[] args) {
      TrafficFeeCalculator calculator = new TrafficFeeCalculator();
      System.out.println(String.format("公交车：%d快人民币",calculator.goToTianJinEye(new ByBus(),10)));
      System.out.println(String.format("didi：%d快人民币",calculator.goToTianJinEye(new ByDiDiExpress(),10)));
      System.out.println(String.format("共享单车：%d快人民币",calculator.goToTianJinEye(new ByShareBicycle(),10)));
   }
}
