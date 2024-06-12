package com.example.java.strategy;

public class Client {
    public static void main(String[] args) {
        TrafficFeeCalculator calculator = new TrafficFeeCalculator();
        System.out.println(String.format("乘坐公交车到天津之眼的花费为：%d快人民币",calculator.goToTianJinEye(new ByBus(),10))); // 输出10元
        System.out.println(String.format("乘坐公交车到天津之眼的花费为：%d快人民币",calculator.goToTianJinEye(new ByDiDiExpress(),10))); // 输出10元
        System.out.println(String.format("乘坐公交车到天津之眼的花费为：%d快人民币",calculator.goToTianJinEye(new BySharedBicycle(),10))); // 输出10元
    }
}
