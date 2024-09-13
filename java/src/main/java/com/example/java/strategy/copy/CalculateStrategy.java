package com.example.java.strategy.copy;

/**
 * 首先定义一个策略接口，规定算法的同一操作
 */
public interface CalculateStrategy {
    int calculate(int distance);
}
