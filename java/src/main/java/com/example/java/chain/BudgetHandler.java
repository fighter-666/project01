package com.example.java.chain;

public interface BudgetHandler {
    void setNextHandler(BudgetHandler nextHandler);

    boolean handler(int amount);
}
