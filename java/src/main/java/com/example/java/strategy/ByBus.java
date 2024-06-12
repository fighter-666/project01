package com.example.java.strategy;

class ByBus implements CalculateStrategy{
    @Override
    public int calculateTrafficFee(int distance) {
        return distance<10?4:6;
    }
}
