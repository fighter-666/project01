package com.example.java.strategy;

class BySharedBicycle implements CalculateStrategy{
    @Override
    public int calculateTrafficFee(int distance) {
        return 2;
    }
}
