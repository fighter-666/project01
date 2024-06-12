package com.example.java.strategy;

public class TrafficFeeCalculator {
    public int goToTianJinEye(CalculateStrategy strategy, int distance){
        return strategy.calculateTrafficFee(distance);
    }
}
