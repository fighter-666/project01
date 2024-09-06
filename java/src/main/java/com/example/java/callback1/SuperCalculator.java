package com.example.java.callback1;

public class SuperCalculator {
    public void add(int a, int b, doJob customer) {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
